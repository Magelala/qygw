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


    ResResult<List<Role>> findAll();



    Role selectByRoleName(String roleName);
}
