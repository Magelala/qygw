package problog.mapper.authorization;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import problog.entity.authorization.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {


    // 查询所有
    List<Menu> queryAll();

    // 多表查询 menu ,menu_role,role
    List<Menu> getAllMenu();

    // 根据角色id 多表查询
    List<Menu> listByRoleId(Long roleId);

}
