package problog.mapper.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import problog.entity.User.Author;


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
}
