package problog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/login")
    public String loginPage(Model model){
        Logo logo = logoService.getById(1);
        request.getSession().setAttribute("src",logo.getSrc());
//        model.addAttribute("src",logo.getSrc());
        return "login";
    }


   // 登录成功跳转
    @RequestMapping(value = "/index")
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
            String name = authentication.getName();
            map.put("userName",name);
            return "index";
        }

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


    }


    @GetMapping("/forget")
    public String forgetPassword(){
        return "findPassword";
    }
}

