package problog.mapper.authorization;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.authorization.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {



    // 查询所有
    List<Role> queryAll();



    // 根据角色名查询
    Role selectByName(String roleName);



}
