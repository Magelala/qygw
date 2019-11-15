package problog.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import problog.entity.authorization.Role;
import problog.entity.response.ResResult;
import problog.service.RoleService;

import java.util.List;

/**
 * @ClassName:RoleController
 * @Author:Timelin
 **/
@RestController
@RequestMapping("/role")
@Api(value = "角色管理接口",tags = "角色的增删改查")
public class RoleController {



    @Autowired
    private RoleService roleService;




    // 查询所有
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Role>> findAll(){

        return roleService.findAll();
    }






}
