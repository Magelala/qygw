package problog.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.User.Author;
import problog.entity.authorization.Role;
import problog.entity.response.ResResult;
import problog.service.AuthorService;
import problog.service.MenuService;
import problog.service.RoleService;
import problog.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Api(value = "用户管理接口",tags = "用户的增删改查")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AuthorService authorService;


    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Author>> findAll(){

        return userService.findAll();
    }


    // 分页查询所有用户信息
    @RequestMapping(value = "/showPage",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Author>> texts(@RequestParam(value = "limit",required = false) Integer limit,
                                         @RequestParam(value = "page",required = false) Integer page){

        return userService.showPage(page,limit);
    }

    // 新增
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult add(@RequestBody Author author){

        return  userService.addAuthor(author);

    }


    // 根据用户id查询
    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<Author> findOne(@RequestParam(value = "id",required = false) Integer id){

        return userService.findOneById(id);
    }
    // 修改
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult update(@RequestBody Author  author){

        return userService.updateAuthor(author);
    }

    //批量删除
    @RequestMapping(value = "deletes")
    @ResponseBody
    public ResResult deleteList(@RequestParam("ids") Integer[] ids){

        return userService.deleteList(ids);
    }


    // 模糊查询
    @RequestMapping(value = "/search")
    @ResponseBody
    public ResResult<List<Author>> search(@RequestBody  Author author,
                                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10")Integer rows){

        return userService.search(author,page,rows);

    }


    // 获取当前登录用户信息
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public ResResult<Author> getCurrentUser(){

        ResResult<Author> resResult =null;

        try{
            //获取当前登录用户id
            User details = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // 判断用户是否登录,提示用户登录
            if(details==null){
                return new ResResult<Author>(0,"该用户还没登陆，无法操作",null);
            }
            String username = details.getUsername();
            // 根据用户名查询
            Author author = authorService.selectByName(username);

            // 根据用户id 获取当前登录用户
            resResult  = userService.findOneById(author.getId());

            return resResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResResult(0,"失败",null);

    }









    /*---------------以下为 页面跳转接口-------------------*/
    @RequestMapping(value = "/userManage",method = RequestMethod.GET)
    public String showUserManage(Model model){
        ResResult<List<Role>> resResult = roleService.findAll();
        List<Role> roleList = resResult.getData();
        model.addAttribute("roleList",roleList);
        return "user/userManage";
    }

    @RequestMapping(value = "/limit",method = RequestMethod.GET)
    public String showLimits(Model model){
        ResResult<List<Role>> resResult = roleService.findAll();
        List<Role> roleList = resResult.getData();
        model.addAttribute("roleList",roleList);
        return "user/limits";
    }

    @RequestMapping(value = "/adminList",method = RequestMethod.GET)
    public String showadminList(Model model){
        ResResult<List<Role>> resResult = roleService.findAll();
        List<Role> roleList = resResult.getData();
        ResResult<Map<String, StringBuffer>> all = menuService.findAll();
        Map<String, StringBuffer> menuList = all.getData();
        model.addAttribute("roleList",roleList);
        model.addAttribute("menuList",menuList);
        return "user/adminList";
    }

    @RequestMapping(value = "/editerList",method = RequestMethod.GET)
    public String showediterList(Model model){
        ResResult<List<Role>> resResult = roleService.findAll();
        List<Role> roleList = resResult.getData();
        ResResult<Map<String, StringBuffer>> all = menuService.findAll();
        Map<String, StringBuffer> menuList = all.getData();

        model.addAttribute("roleList",roleList);
        model.addAttribute("menuList",menuList);
        return "user/editerList";
    }

}
