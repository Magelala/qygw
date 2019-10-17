package problog.security.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import problog.security.CustomAuthenticationFailureHandler;
import problog.security.CustomAuthenticationSuccessHandler;
import problog.security.service.EmailUserDetailsService;

/**
 * @ClassName:EmailCodeAuthenticationSecurityConfig
 * @Author:Timelin
 **/

@Component
public class EmailCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private EmailUserDetailsService emailUserDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        EmailCodeAuthenticationFilter emailCodeAuthenticationFilter = new EmailCodeAuthenticationFilter();
        emailCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        emailCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        emailCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        EmailCodeAuthenticationProvider emailCodeAuthenticationProvider = new EmailCodeAuthenticationProvider();
        emailCodeAuthenticationProvider.setEmailUserDetailsService(emailUserDetailsService);

        http.authenticationProvider(emailCodeAuthenticationProvider)
                .addFilterAfter(emailCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
