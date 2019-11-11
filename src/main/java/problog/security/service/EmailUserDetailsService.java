package problog.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import problog.entity.User.Author;
import problog.service.AuthorService;

import java.util.ArrayList;

/**
 * 自定义封装用户邮箱和验证码、权限
 * @ClassName:CustomUserDetailsService
 * @Author:Timelin
 **/
@Service("emailUserDetailsService")
public class EmailUserDetailsService implements UserDetailsService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AuthorService authorService;


    @Override
    public UserDetails loadUserByUsername(String email_Code ) throws UsernameNotFoundException {

        logger.info("user{} 登录的用户是", email_Code);
        // 定义一个权限集合
        ArrayList<GrantedAuthority>   authorities= new ArrayList<>();

        // 从数据库总取出用户信息
        Author author = authorService.selectByEmail(email_Code);


        // 判断用户是否存在
        if(author==null){
            throw new UsernameNotFoundException("用户邮箱错误！");

        }
        // 分配角色,全部登录成功的用户都分配固定的角色
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 返回UserDetails实现类
        return new User(author.getEmail(),author.getPassword(),authorities);
    }
}
