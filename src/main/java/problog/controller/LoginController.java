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
import problog.entity.logo.Logo;
import problog.service.LogoService;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogoService logoService;


    /**
     *后台登录操作
     * @param
     * @param
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
        Logo logo = logoService.getById(1);
        request.getSession().setAttribute("src",logo.getSrc());
        return "login";
    }


   // 登录成功跳转
    @RequestMapping(value = "/user/success")
    public String loginSuccess(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
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


    //可以不需要新写一个处理退出的请求,直接使用登录时的哪个请求.
    @RequestMapping(value = "/loginOutSuccess")
    public String loginOut(){
        request.getSession().removeAttribute("src");
        return "login";
    }

    @RequestMapping("/email/code")
    public void  email(String emailCode, HttpSession session,HttpServletResponse response) throws IOException {
        int code = (int) Math.ceil(Math.random()* 9000+1000);
        Map<String,Object> map = new HashMap<>(16);
        map.put("email",emailCode);
        map.put("code",code);
        session.setAttribute("emailCode",map);
        //设置当前会话时间维持1分钟
        session.setMaxInactiveInterval(3600);

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

