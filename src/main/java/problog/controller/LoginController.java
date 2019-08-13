package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : shengjun
 * @Date : create in 2019-8-7
 */
@Controller
public class LoginController {

    private static String username = "admin";
    private static String password = "123456";

    /**
     *后台登录操作
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */

    @PostMapping(value = "/user/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (LoginController.username.equals(username) && LoginController.password.equals(password)){
            httpServletRequest.getSession().setAttribute("user",username);
            String remember = httpServletRequest.getParameter("remember");
            //记住用户的功能实现，保存在Cookie中
            if (remember.equals("true")){
                Cookie cookie = new Cookie("username",username);
                cookie.setPath("/");
                cookie.setMaxAge(7*60*60*24);
                httpServletResponse.addCookie(cookie);
            }
            return "redirect:/index.html";
        }else{
           return "/login";
        }
    }
}

