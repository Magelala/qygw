package problog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import problog.interceptor.LoginInterceptor;


import java.nio.charset.Charset;

/**
 * @Author : shengjun
 * @Date : create in
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public HandlerInterceptor getBackInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 直接访问localhost:8080/toLogin就跳转到login.html页面了
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //视图跳转控制器
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/index.html").setViewName("/index");
    }

    /**
     * 用来配置静态资源，比如html,js,css
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    /**
     * 注册拦截器，我们自己写好的拦截器需要在这里添加注册才能生效
     * addPathPatterns("/admin) 表示拦截所有的请求
     * excludePathPatterns("/asserts") 表示js、css、img都可以访问
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/","/user/login","/index","/asserts/");
    }
}
