package problog.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import problog.entity.User.Author;
import problog.entity.response.ResResult;
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

    // 修改，单表修改
    @Override
    public ResResult updateAuthor(Author author) {

        ResResult resResult = null;
        try {
            //判空
            if(null == author || "".equals(author)){
                return resResult.fail(0);
            }
            Author oldAuthor = authorMapper.selectById(author.getId());
            //校验用户id,新旧都要检验，
            Integer authorId = author.getId();
            if(!authorId.equals(oldAuthor.getId())|| !authorId.equals(author.getId())){
                return resResult.fail(0);
            }


            if(!author.getPassword().equals(oldAuthor.getPassword())){

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if(!bCryptPasswordEncoder.matches(author.getPassword(),oldAuthor.getPassword())){
                    // 如果新旧密码不一，就对新密码加密
                    String  password = bCryptPasswordEncoder.encode(author.getPassword());
                    author.setPassword(password);

                }else {
                    String  password = bCryptPasswordEncoder.encode(author.getPassword());
                    author.setPassword(password);
                }
              /*  // 如果新旧密码不一，就对新密码加密
                 String  password = new BCryptPasswordEncoder().encode(author.getPassword());
                author.setPassword(password);*/
            }

            int i = authorMapper.updateById(author);
            resResult = new ResResult(0,"更新成功",null);
            resResult.setCount(1);
            return resResult;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resResult.fail(0);


    }

}
