package problog.security.email;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import problog.security.service.EmailUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 邮箱登录鉴权 Provider , 要求实现 AuthenticcationProvider 接口
 * @ClassName:EmailCodeAuthenticationProvider
 * @Author:Timelin
 **/
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {

    private EmailUserDetailsService emailUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailCodeAuthenticationToken authenticationToken = (EmailCodeAuthenticationToken) authentication;
        String email_Code = (String)authenticationToken.getPrincipal();

        checkEmailCode(email_Code);
        // 自定义封装用户信息
        UserDetails userDetails = emailUserDetailsService.loadUserByUsername(email_Code);

        // 此时鉴权成功后 ，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        EmailCodeAuthenticationToken authenticationResult = new EmailCodeAuthenticationToken(userDetails.getAuthorities(),userDetails);

        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    // 校验验证码
    private void checkEmailCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCoden = request.getParameter("code");

        if(inputCoden==null||inputCoden.equals("")){
            throw new BadCredentialsException("账号或验证码错误！");
        }

        //
        Map<String, Object> emailCode = (Map<String, Object>) request.getSession().getAttribute("emailCode");
        if(emailCode ==null){
            throw new BadCredentialsException("账号或验证码错误，请输入");
        }

        // 登录账号
        String applyEmail = (String) emailCode.get("email");

        int code = (int) emailCode.get("code");
        if(!applyEmail.equals(mobile)){
            //throw new BadCredentialsException("申请的账号与登录账号不一致");
            throw new BadCredentialsException("账号或验证码错误！");

        }

        if(code != Integer.parseInt(inputCoden)){
            throw new BadCredentialsException("验证码错误");

        }

    }

    @Override
    public boolean supports(Class<?> authenticate) {
        // 判断 authentication 是不是 EmailCodeAuthenticationToken 的子类或子接口

        return EmailCodeAuthenticationToken.class.isAssignableFrom(authenticate);
    }

    public EmailUserDetailsService getEmailUserDetailsService() {
        return emailUserDetailsService;
    }

    public void setEmailUserDetailsService(EmailUserDetailsService emailUserDetailsService) {
        this.emailUserDetailsService = emailUserDetailsService;
    }

}
