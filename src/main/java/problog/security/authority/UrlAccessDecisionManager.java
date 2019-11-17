package problog.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import problog.security.service.CustomUserDetailsService;

import java.util.Collection;
import java.util.Iterator;

/**
 * @ClassName:UrlAccessDecisionManager
 * @Author:Timelin
 **/
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {


    @Autowired
    CustomUserDetailsService userDetailsService;

    // 校验登录拥有的角色权限，与用户请求url所需要的权限角色是否一致
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {


        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();

           // 没有登录,
            if ("anonymousUser".equals(authentication.getPrincipal())){
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                }
            }

            // 动态刷新用户数据
            String name = authentication.getName();
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);

            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }

        }
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
