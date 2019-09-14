package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import problog.entity.User.User;
import problog.utils.FindUser;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
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

    static Map<Integer,User> data;

    static{
        //定义一个数据库，存放用户数据
        data = new HashMap<Integer, User>();
        data.put(1,new User("admin","88888888"));
        data.put(2,new User("zhangsan","123456"));
        data.put(3,new User("lisi","00000000"));
    }

    /**
     *后台登录操作
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */

    @PostMapping(value = "/user/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Map<String,Object> map){

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        //从数据库中查找该用户名
        User user = FindUser.findUserByName(LoginController.data,username);
        if (null != user && user.getUsername().equals(username) && user.getPassword().equals(password)){
            httpServletRequest.getSession().setAttribute("user",user);
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
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String username = httpServletRequest.getParameter("username");
        if (null != session){
            session.removeAttribute("user");
        }
        return "login";
    }
}

