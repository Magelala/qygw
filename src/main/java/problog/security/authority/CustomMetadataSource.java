package problog.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import problog.entity.authorization.Menu;
import problog.entity.authorization.Role;
import problog.service.MenuService;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName:CustomMetadataSource
 * @Author:Timelin
 **/

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
   // DefaultFilterInvocationSecurityMetadataSource
    @Autowired
    private MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    // 查询数据库所有的权限，遍历所有的权限与用户请求的url进行对比,校验用户的请求url是否存在于数据库
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        // 请求url:‘/’，无条件通过
        if (antPathMatcher.match("/", requestUrl)) {
            return null ;
        }
        // 每个用户都有的权限
        if(antPathMatcher.match("/login/**", requestUrl)){
            return null;
        }

        // 如果是邮箱发送请求，无条件通过
        if (antPathMatcher.match("/email/**", requestUrl)) {
            return null ;
        }
        // 如果报错，无条件通过
        if (antPathMatcher.match("/error/**", requestUrl)) {
            return null ;
        }

        List<Menu> allMenu = menuService.getAllMenu();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)
                    &&menu.getRoles().size()>0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(values);
            }
        }




        // 每个成功登录的用户都拥有的权限
        if(antPathMatcher.match("/setting/**", requestUrl)){
            return SecurityConfig.createList("ROLE_ANONYMOUS");
        }
        if(antPathMatcher.match("/index/**", requestUrl)){
            return SecurityConfig.createList("ROLE_ANONYMOUS");
        }

        if(antPathMatcher.match("/upload/**", requestUrl)){
            return null;
        }
        if(antPathMatcher.match("/loginOutSuccess/**", requestUrl)){
            return null;
        }


        //没有匹配上的资源，都是登录访问或权限不足
        return SecurityConfig.createList("ROLE_NO");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
