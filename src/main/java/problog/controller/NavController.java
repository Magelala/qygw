package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import problog.entity.nav.Nav;
import problog.entity.response.ResResult;
import problog.service.NavService;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 21:46
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Nav>> listParentNav(){
        ResResult<List<Nav>> resResult = new ResResult<>();
        List<Nav> list = navService.allParentNav();
        resResult.setCount(list.size());
        resResult.setCode(0);
        resResult.setData(list);
        return resResult;
    }

    @RequestMapping(value = "/allSub",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Nav>> listSubNav(@RequestParam("pid") Integer pid){
        ResResult<List<Nav>> resResult = new ResResult<>();
        List<Nav> list = navService.allSubNav(pid);
        resResult.setCode(0);
        resResult.setData(list);
        resResult.setCount(list.size());
        return resResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Nav> delete(@RequestParam("id") Integer id){
        ResResult<Nav> resResult = new ResResult<>();
        Nav nav = navService.getById(id);
        if (null != nav){
            int i = navService.deleteById(id);
            resResult.setData(nav);
            resResult.setCode(200);
            resResult.setMsg("删除成功");
            resResult.setCount(i);
        }else{
            resResult.setMsg("删除失败");
            resResult.setCount(0);
            resResult.setData(null);
            resResult.setCode(500);
        }
        return resResult;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Nav> addParent(@RequestBody Nav nav){
        ResResult<Nav> resResult = new ResResult<>();
        nav.setPid(0);
        int i = navService.addParentNav(nav);
        resResult.setCount(i);
        resResult.setCode(200);
        resResult.setMsg("添加父导航成功");
        resResult.setData(nav);
        return resResult;
    }

    @RequestMapping(value = "/addSub",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Nav> addSub(@RequestBody Nav nav){
        ResResult<Nav> resResult = new ResResult<>();
        int i = navService.addSubNav(nav);
        resResult.setCount(i);
        resResult.setCode(200);
        resResult.setMsg("添加子导航成功");
        resResult.setData(nav);
        return resResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Nav> update(@RequestBody Nav nav){
        ResResult<Nav> resResult = new ResResult<>();
        Nav nav1 = navService.getById(nav.getId());
        if (null != nav1){
            int i = navService.updateById(nav);
            resResult.setCode(200);
            resResult.setData(nav);
            resResult.setCount(i);
            resResult.setMsg("修改成功");
        }else{
            resResult.setCount(0);
            resResult.setData(null);
            resResult.setCode(500);
            resResult.setMsg("修改失败");
        }
        return resResult;
    }

    @RequestMapping(value = "/navSub",method = RequestMethod.GET)
    public String navSub(){
        return "nav/subNav";
    }

    @RequestMapping(value = "/nav",method = RequestMethod.GET)
    public String navParent(){
        return "nav/nav";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addParent(){
        return "nav/addParent";
    }

    @RequestMapping(value = "/addSub",method = RequestMethod.GET)
    public String addSub(){
        return "nav/addSub";
    }

}
