package problog.mapper.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import problog.entity.User.Author;

import java.util.List;


@Mapper
public interface AuthorMapper extends BaseMapper<Author> {
    // 根据用户名查询
    //@Select("select * from author where user_name= #{username}")
    Author selectByName(String username);

    // 根据id查询
    //@Select("select * from author where id = #{id}")
    Author selectById(Integer id);

    // 根据用户邮箱查询
    Author selectByEmail(String email);


    // 查询所有
    List<Author> queryAll();

    // 分页查询
    /*@Select("select * from author order  by  id  asc limit #{limit},#{page}")*/
    List<Author> selectPage(@Param("limit") Integer limit, @Param("page") Integer page);


    // 多表查询 author,user_role,role
    List<Author> getAllUser();

    // 根据用户id 多表查询 author,user_role,role
    Author getAllById(Integer id);

    // 多表 模糊查询 根据用户名称：userName 或 角色名称：name
    List<Author> search(@Param("userName") String userName ,@Param("name")String  name);
}
