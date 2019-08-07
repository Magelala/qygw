package problog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import problog.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : shengjun
 * @Date : create in 2019-8-7
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static String username = "static.admain";
    private static String password = "123456";

    /**
     *后台登录操作
     * @param user
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @PostMapping("/login")
    public String login(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)){
            httpServletRequest.getSession().setAttribute("user",user);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/static.admain/index.html");
        }else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/toLogin");
        }
        return null;
    }
}
