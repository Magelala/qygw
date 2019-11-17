package problog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import problog.entity.authorization.Role;
import problog.entity.response.ResResult;
import problog.mapper.authorization.RoleMapper;
import problog.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:RoleServiceImpl
 * @Author:Timelin
 **/
@Service(value = "roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;



    // 查询所有角色
    @Override
    public ResResult<List<Role>> findAll() {



        List<Role> roles = roleMapper.queryAll();
        ResResult<List<Role>> resResult =  new ResResult<List<Role>>(0,"查询所有成功",roles);
        resResult.setCount(roles.size());

        return resResult;
    }


    // 根据角色名称查询
    @Override
    public Role selectByRoleName(String roleName) {
        return roleMapper.selectByName(roleName);
    }

    // 根据角色id查询
    @Override
    public Role selectById(Integer rid) {
        try {

            return roleMapper.selectById(rid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
