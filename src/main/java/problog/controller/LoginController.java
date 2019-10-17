package problog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import problog.service.MailService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : shengjun
 * @Date : create in 2019-8-7
 */
@Controller
@ApiIgnore
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);



    @Autowired
    private MailService mailService;


   /* static Map<Integer,User> data;

    static{
        //定义一个数据库，存放用户数据
        data = new HashMap<Integer, User>();
        data.put(1,new User("admin","88888888"));
        data.put(2,new User("zhangsan","123456"));
        data.put(3,new User("lisi","00000000"));
    }
*/
    /**
     *后台登录操作
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */

   /* @PostMapping(value = "/user/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Map<String,Object> map){

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        //从数据库中查找该用户名
        User user = FindUser.findUserByName(LoginController.data,username);
        if (null != user && user.getUsername().equals(username) && user.getPassword().equals(password)){
            httpServletRequest.getSession().setAttribute("user",user);
            httpServletRequest.getSession().setAttribute("authorId",1);
            String remember = httpServletRequest.getParameter("remember");
            //记住用户名的功能实现，保存在Cookie中
            if (remember.equals("true")){
                Cookie cookie = new Cookie("username",username);
                cookie.setPath("/");
                cookie.setMaxAge(7*60*60*24);
                httpServletResponse.addCookie(cookie);
            }
            return "redirect:/index.html";
        }else{
            httpServletRequest.getSession().setAttribute("user",null);
            map.put("msg","用户名密码错误");
           return "login";
        }
    }*/
    /*@RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String username = httpServletRequest.getParameter("username");
        if (null != session){
            session.removeAttribute("user");
        }
        return "login";
    }*/

    @RequestMapping(value = "/user/login")
    public String loginPage( Map<String,Object> map){

        return "login";
    }


   // 登录成功跳转
    @RequestMapping(value = "/user/success")
    public String loginSuccess(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            //如果用户为空，跳转到错误的页面
            map.put("msg","用户名密码错误");
            return "login";
        }else{
            // 登陆成功
            return "index";
        }

    }



    @RequestMapping(value = "/loginOutSuccess")
    public String loginOut( ){

        return "redirect: /";
    }

    @RequestMapping("/email/code")
    @ResponseBody
    public void  email(String emailCode, HttpSession session,HttpServletResponse response) throws IOException {
        int code = (int) Math.ceil(Math.random()* 9000+1000);
        Map<String,Object> map = new HashMap<>(16);
        map.put("email",emailCode);
        map.put("code",code);
        session.setAttribute("emailCode",map);


        // 发送验证码到指定邮箱
      // emailUtil.sendMail(code,emailCode);
        System.out.println(Integer.toString(code));

        // 标题
        String subject = "小鱼发送文本邮件测试";
        // 邮件内容
        String content ="您好！欢迎登录magelala，你的登录验证码是："+code;
        mailService.sendSimpleMail(emailCode,subject,content);



        logger.info("{}: 为{} 设置邮箱验证码：{}",session.getId(),emailCode,code);

       // response.sendRedirect("/login");
    }

    // 权限管理
/*
    @RequestMapping(value = "/main")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String mianPage( ){

        return "index";
    }
*/






    @GetMapping("/forget")
    public String forgetPassword(){
        return "findPassword";
    }


}

