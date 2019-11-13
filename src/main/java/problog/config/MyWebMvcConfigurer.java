package problog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import problog.constants.Constants;
import problog.interceptor.LoginInterceptor;


import java.nio.charset.Charset;

/**
 * @Author : shengjun
 * @Date : create in
 * addResourceHandlers方法添加了修改上传图片，内部服务器找不到图片资源的一个文件映射，必须加上
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


   @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //视图跳转控制器
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/loginOut").setViewName("/login");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/main").setViewName("/index");
        registry.addViewController("/article").setViewName("/article");
        registry.addViewController("/article.html").setViewName("/article");
        registry.addViewController("/advertisement.html").setViewName("/advertise");
        registry.addViewController("/advertise").setViewName("/advertise");
        registry.addViewController("/forgetPassword.html").setViewName("/forget");
        registry.addViewController("/forgetPassword").setViewName("/forget");
        registry.addViewController("/user.html").setViewName("/user");
        registry.addViewController("/user").setViewName("/user");
        registry.addViewController("/setting.html").setViewName("/setting");
        registry.addViewController("/setting").setViewName("/setting");

       registry.addViewController("/user/userManage").setViewName("/user/userManage");
       registry.addViewController("/user/limit").setViewName("/user/limits");
       registry.addViewController("/adminList.html").setViewName("/user/adminList");
       registry.addViewController("/editerList.html").setViewName("/user/editerList");
       registry.addViewController("/news.html").setViewName("/news");
       registry.addViewController("/foot.html").setViewName("/foot/index");
    }




    /**
     * 用来配置静态资源，比如html,js,css
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/picture/**").addResourceLocations(Constants.STATIC_RESOURCE_MAPPING_PATH);
    }


    /**
     * 注册拦截器，我们自己写好的拦截器需要在这里添加注册才能生效
     * addPathPatterns("/admin) 表示拦截所有的请求
     * excludePathPatterns("/asserts") 表示js、css、img都可以访问
     * @param registry
     * 测试接口，注释拦截器
     */
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/","/user/login","/css/**","/js/**","/img/**","/fonts/**","/bootstrap/**");
    }
*/

}
