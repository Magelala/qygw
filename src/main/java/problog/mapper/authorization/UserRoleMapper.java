package problog.mapper.authorization;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import problog.entity.authorization.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    // 根据用户 id 查询

    List<UserRole> ListByUserId(@Param("urid") Integer urid);


    // 查询
    List<UserRole> getAllUser();



    // 添加



    // 修改


    // 删除
    // 根据用户id 和 角色id 查询 缺一不可
    int deleteByUridAndrid(@Param("urid") Integer urId, @Param("rid") Integer rId);

    // 根据用户id 删除
    int deleteByUrid(@Param("urid") Integer id);
}
