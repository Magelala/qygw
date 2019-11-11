package problog.security.email;


import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 邮箱登录的鉴权过滤器， 模仿 UsernamePasswordAuthenticationFilter
 * @ClassName:EmailCodeAuthenticationFilter
 * @Author:Timelin
 **/
public class EmailCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /* form 表单邮箱号的字段名称*/
    public static final String SPRING_SECURITY_FORM_Email_KEY = "emailCode";

    private String emailParameter = SPRING_SECURITY_FORM_Email_KEY;

    /* 是否POST方式*/
    private boolean postOnly = true;

    public EmailCodeAuthenticationFilter() {
        // 邮箱登录的请求 post 方式 的/email/login
        super(new AntPathRequestMatcher("/email/login","POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

            String email = obtainMobile(request);
            if (email == null) {
                email = "";
            }

        email = email.trim();
            EmailCodeAuthenticationToken authRequest = new EmailCodeAuthenticationToken(email);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

    }

    private String obtainMobile(HttpServletRequest request) {
        return  request.getParameter(emailParameter);
    }


    protected void setDetails(HttpServletRequest request, EmailCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public String getEmailParameter() {
        return emailParameter;
    }

    public void setEmailParameter(String emailParameter) {
        Assert.hasText(emailParameter,"email parameter must not be empty or null");

        this.emailParameter = emailParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }



}
