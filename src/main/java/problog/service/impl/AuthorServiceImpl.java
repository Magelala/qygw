package problog.service.impl;

import org.springframework.stereotype.Service;
import problog.entity.User.Author;
import problog.mapper.User.AuthorMapper;
import problog.service.AuthorService;

import javax.annotation.Resource;

/**
 * @ClassName:AuthorServiceImpl
 * @Author:Timelin
 **/
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private AuthorMapper authorMapper;


    // 根据用户名称查询
    @Override
    public Author selectByName(String username) {
        return authorMapper.selectByName(username);
    }

    // 根据用户ID查询
    @Override
    public Author selectById(Integer id) {
        return authorMapper.selectById(id);
    }

    @Override
    public Author selectByEmail(String email_code) {
        return authorMapper.selectByEmail(email_code);
    }
}
