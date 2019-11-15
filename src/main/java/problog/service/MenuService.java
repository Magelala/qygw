package problog.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.authorization.Menu;
import problog.entity.response.ResResult;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface MenuService {

    // 查询所有
    ResResult<Map<String, StringBuffer>> findAll();


    // 多表查询 menu ,menu_role,role
    List<Menu> getAllMenu();

    // 根据角色id多表查询
    List<Menu> listByRoleId(Long roleId);

    // 给角色分配 权限
    ResResult addMenuRole(Integer rid, String mid);
}
