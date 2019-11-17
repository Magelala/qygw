package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.authorization.Role;
import problog.entity.response.ResResult;

import java.util.List;

/**
 * @ClassName:RoleService
 * @Author:Timelin
 **/
@Service
@Transactional
public interface RoleService {

    // 查询所有角色
    ResResult<List<Role>> findAll();

    // 根据角色名称查询
    Role selectByRoleName(String roleName);

    // 根据角色id查询
    Role selectById(Integer rid);
}
