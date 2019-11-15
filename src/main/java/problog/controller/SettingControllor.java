package problog.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.User.Author;
import problog.entity.response.ResResult;
import problog.service.AuthorService;
import problog.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:SettingControllor
 * @Author:Timelin
 **/
@Controller
@RequestMapping("/setting")
@Api(value = "设置接口",tags = "用户的设置操作")
public class SettingControllor {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private HttpServletResponse response;

    // 用户修改密码
    @RequestMapping("updatePassword")
    @ResponseBody
    public ResResult updatePwd(@RequestParam("password") String password, @RequestParam("oldPassword") String oldPassword ){

        ResResult resResult = null;

        try{


            // 校验用户是否登录
            Author author = checkoutLogin();


            //对 旧密码然后进行校验
            if(!oldPassword.equals(author.getPassword())) {

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if(!bCryptPasswordEncoder.matches(oldPassword,author.getPassword())){
                    // 密码不正确放回给前端
                    return  new ResResult<>(0,"初始密码输入错误",null);
                }


            }


            author.setPassword(password);
             resResult = authorService.updateAuthor(author);


            return resResult;


        }catch (Exception e){
            e.printStackTrace();
        }
        return resResult.fail(0) ;


    }



    // 修改 个人资料
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult update(@RequestBody Author author){

        return authorService.updateAuthor(author);
    }



    /*---------------以下为 页面跳转接口-------------------*/


    @RequestMapping(value = "/setting",method = RequestMethod.GET)
    public String setting(Model model){
        return "setting/setting";
    }


    @RequestMapping(value = "/myProfile",method = RequestMethod.GET)
    public String profile(Model model){

        try{

            // 校验用户是否登录
            Author author = checkoutLogin();

            // 根据用户id 获取当前登录用户
            ResResult<Author> resResult = userService.findOneById(author.getId());
            model.addAttribute("user",resResult.getData());
            return "setting/myProfile";
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }


    @RequestMapping(value = "/updatePwd",method = RequestMethod.GET)
    public String password(Model model){

        try{
            // 校验用户是否登录
            Author author = checkoutLogin();

            model.addAttribute("user",author);

            return "setting/updatePwd";

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


    // 校验用户是否登录
    public Author checkoutLogin() throws IOException {


        try{
            //获取当前登录用户id
            User details = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // 判断用户是否登录,提示用户登录
            if(details==null){


                response.sendRedirect("login");
            }
            String username = details.getUsername();
            // 根据用户名查询
            Author author = authorService.selectByName(username);

            return author;

        }catch (Exception e){
            e.printStackTrace();

        }

        return null;


    }

}
