package problog.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import problog.entity.authorization.Menu;
import problog.entity.response.ResResult;
import problog.service.MenuService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:MenuController
 * @Author:Timelin
 **/

@RestController
@RequestMapping("/menu")
@Api(value = "权限管理接口",tags = "权限的增删改查")
public class MenuController {


    @Autowired
    private MenuService menuService;


    // 查询所有
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<Map<String, StringBuffer>> findAll(){
        return menuService.findAll();
    }


    // 给角色添加权限
    @RequestMapping(value = "/addmenuRole",method = RequestMethod.POST)
    @ResponseBody
    public ResResult add(@RequestParam(value = "roleId") Integer  rid, @RequestParam(value = "menuIds") String mid){


        return  menuService.addMenuRole(rid,mid);

    }








}
