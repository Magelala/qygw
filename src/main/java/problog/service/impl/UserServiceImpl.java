package problog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import problog.entity.User.Author;
import problog.entity.authorization.UserRole;
import problog.entity.response.ResResult;
import problog.mapper.User.AuthorMapper;
import problog.mapper.authorization.RoleMapper;
import problog.mapper.authorization.UserRoleMapper;
import problog.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Author:Timelin
 **/

@Service(value = "userSerivce")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;



    // 查询所有 多表查询
    @Override
    public ResResult<List<Author>> findAll() {


       ResResult<List<Author>> resResult =null;

        try {

 List<Author> authorList = authorMapper.getAllUser();
        resResult = new ResResult<List<Author>>(0,"所有用户信息",authorList);
        resResult.setCount(authorList.size()); //设置总数
        return resResult;

        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResResult(0,"失败",null);
    }

    // 分页查询所有用户
    @Override
    public ResResult<List<Author>> showPage(Integer page, Integer limit) {

        ResResult<List<Author>> resResult =null;

        try {
            List<Author> authorPage = authorMapper.selectPage((page-1)*limit,limit);


            List<Author> authorList = authorMapper.queryAll();
            resResult = new ResResult<>(0,"分页查询用户信息",authorPage);

            resResult.setLimit(limit);
            resResult.setPage(page);
            resResult.setCount(authorList.size()); //设置总数

            return resResult;


        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResResult(0,"失败",null);

    }

    //根据id 多表查询
    @Override
    public ResResult<Author> findOneById(Integer id) {

        ResResult<Author> resResult =null;

        try {
            //判空
            if(null == id  || "".equals(id)){
                return new ResResult<>(0,"失败",null);
            }

            Author author = authorMapper.getAllById(id);
            // 判空
            if(null == author && "".equals(author)){
                resResult = new ResResult<>(0,"根据用户id查询，没有该用户",null);
                resResult.setCount(0);
                return resResult;
            }

            resResult = new ResResult<>(0,"根据用户id查询",author);
            resResult.setCount(1);
            return resResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult<>(0,"失败",null);

    }

    // 修改 多表修改
    @Override
    public ResResult updateAuthor(Author author) {

        ResResult resResult = null;
        try {
            //判空
            if(null == author || "".equals(author)){
                return new ResResult<>(0,"失败",null);
            }
            Author oldAuthor = authorMapper.selectById(author.getId());
            //校验用户id,新旧都要检验，
            Integer authorId = author.getId();
            if(!authorId.equals(oldAuthor.getId())|| !authorId.equals(author.getId())){
                return new ResResult<>(0,"失败",null);
            }




            // 校验密码

            if(!author.getPassword().equals(oldAuthor.getPassword())){
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if(!bCryptPasswordEncoder.matches(author.getPassword(),oldAuthor.getPassword())){
                    // 如果新旧密码不一，就对新密码加密
                    String  password = bCryptPasswordEncoder.encode(author.getPassword());
                    author.setPassword(password);

                }else {
                    String  password = new BCryptPasswordEncoder().encode(author.getPassword());
                    author.setPassword(password);
                }

            }

            authorMapper.updateById(author);

            // 修改角色 先删除原来的后添加新的角色
            Integer rId = Integer.valueOf(author.getRole());
            int i = userRoleMapper.deleteByUridAndrid(authorId,rId);
            UserRole userRole = new UserRole();
            userRole.setUrid(authorId);
            userRole.setRid(rId);
            int user_role  = userRoleMapper.insert(userRole);
            resResult = new ResResult<>(0,"更新成功",null);
            resResult.setCount(user_role);


            return resResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult<>(0,"失败",null);


    }

    // 批量删除 多表删除
    @Override
    public ResResult deleteList(Integer[] ids) {
        ResResult resResult = null;

        try {
            int count=0;
            if(ids !=null && ids.length>0){
                // 遍历id,查询是否有该用户
                for (Integer id: ids){

                    // 先删除用户所拥有的角色
                   int delete = userRoleMapper.deleteByUrid(id);

                    //再 根据id删除用户信息
                    authorMapper.deleteById(id);
                    count++;

                }
            }
            resResult =new ResResult(0,"删除成功",null);
            resResult.setCount(count);
            return resResult ;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resResult.fail(0);
    }

    // 新增  多表新增
   @Override
    public ResResult addAuthor(Author author) {


        ResResult resResult = null;
        try {

            //判空
            if(null == author || "".equals(author)){
                return resResult.fail(0);
            }
            //密码不能为空
            String password = author.getPassword();
            if(StringUtils.isEmpty(password)||"".equals(password)){

                return new ResResult(0,"请输入密码",null);
            }
            // 查询用户名是否存在
            Author oldAuthor = authorMapper.selectByName(author.getUserName());

            if(null != oldAuthor ){
                return new ResResult(0,"该用户名已经存在，请重新输入",null);
            }


            // 密码加密
            password = new BCryptPasswordEncoder().encode(password);
            author.setPassword(password);



            // 添加到数据库
            int insert = authorMapper.insert(author);
            // 分配角色
            Integer urId = author.getId();
            Integer rid = Integer.valueOf(author.getRole());
            UserRole userRole = new UserRole();
            userRole.setUrid(urId);
            userRole.setRid(rid);
            int user_role  = userRoleMapper.insert(userRole);

            if(insert>0){
                resResult =new ResResult(0,"新增成功",null);
                resResult.setCount(insert);
                return resResult;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(0,"失败",null);
    }




    // 模糊查询
    @Override
    public ResResult<List<Author>> search(Author author, Integer page, Integer rows) {

        ResResult< List<Author>> resResult =null;
        try {


            List<Author> list = new ArrayList<>();

            // 1.根据用户的username查询 2. 根据用户角色邮箱查询 3.根据name查询
            if(!StringUtils.isEmpty(author.getUserName())||!StringUtils.isEmpty(author.getRole())){
                list = authorMapper.search(author.getUserName(),author.getRole());
            }

            resResult = new ResResult<>(0,"搜索成功",list);
            resResult.setCount(list.size());
            return resResult;

        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResResult(0,"失败",null);
    }
}
