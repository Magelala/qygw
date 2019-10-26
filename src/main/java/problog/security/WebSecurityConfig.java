package problog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import problog.security.email.EmailCodeAuthenticationSecurityConfig;
import problog.security.service.CustomUserDetailsService;
import problog.security.service.EmailUserDetailsService;

import javax.sql.DataSource;

/**
 * springSecurity核心配置类
 * @ClassName:WebSecurityConfig
 * @Author:Timelin
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private  CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private EmailCodeAuthenticationSecurityConfig emailCodeAuthenticationSecurityConfig;




    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

   /*
    * 返回RememberMeServices实例
    * */
   /* @Bean
    public RememberMeServices rememberMeServices(){
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此时需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);

        // 此处的key可以为任意非空值（null或“”），但必须和起前面
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", userDetailsService, rememberMeTokenRepository);

        //该参数不是必须的，默认值为“remember-me" ,但如果设置必须和页面复选框的name一致
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
    }*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(emailCodeAuthenticationSecurityConfig).and()
                .authorizeRequests()
             /* .antMatchers("/article/**").hasAnyRole("USER")
               .antMatchers("/advertise/**").hasAnyRole("USER")
               .antMatchers("/companyProfile/**").hasAnyRole("USER")
               .antMatchers("/nav/**").hasAnyRole("USER")
               .antMatchers("/setting/**").hasAnyRole("USER")
               .antMatchers("/user").hasAnyRole("USER")
               .antMatchers("/upload/**").hasAnyRole("USER")*/
               .antMatchers("/**").hasAnyRole("USER")
                // 允许邮箱验证码发送URL
              .antMatchers("/email/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登录页
               .formLogin().loginPage("/user/login")
                // 设置自定义登录成功和失败
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler).permitAll()
                .and()
                .logout()
                   .logoutUrl("/loginOut")// 注销接口
                    .logoutSuccessUrl("/loginOutSuccess").permitAll()  // 注销成功跳转接口
                    .invalidateHttpSession(true)// 指定是否在注销时让HttpSession无效

                .and()
               .rememberMe()// 自动登录
                  .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60*24*7)// 设置7天有效
               // .rememberMeServices(rememberMeServices())
               // .key("INTERNAL_SECRET_KEY")
                .userDetailsService(userDetailsService).and()
                // session管理
              .sessionManagement()
                .invalidSessionUrl("/");

        // 关闭CSRF跨域
        http.csrf().disable();
        // Spring Security4默认是将'X-Frame-Options' 设置为 'DENY',所以修改为‘disable’
        http.headers().frameOptions().disable();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置拦截器忽略文件夹，可以对静态资源放行

        web.ignoring().antMatchers("/bootstrap/**","/img/**","/images/**","/fonts/**","/font/**","/css/**","/js/**","/lay/**","/node_modules/**","/upload/**","/lay/**","/layui.all.js","/layui.js");
    }



}
