package problog.service;


import org.springframework.stereotype.Service;
import problog.entity.User.Author;

@Service
public interface AuthorService {
    // 根据用户名查询
    Author selectByName(String username);

    // 根据id查询
    Author selectById(Integer id);

    // 根据邮箱查询
    Author selectByEmail(String email_code);
}
