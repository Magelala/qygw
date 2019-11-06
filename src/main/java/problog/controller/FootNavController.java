package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.nav.FootNav;
import problog.entity.response.ResResult;
import problog.service.FootNavService;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/5 21:28
 */
@Controller
@RequestMapping("/footNav")
public class FootNavController {

    @Autowired
    private FootNavService footNavService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<FootNav> add(@RequestBody FootNav footNav){
        ResResult<FootNav> resResult = new ResResult<>();
        if (footNav!=null){
            int i =footNavService.add(footNav);
            resResult.setCount(i);
            resResult.setData(footNav);
            resResult.setMsg("添加成功");
            resResult.setCode(200);
        }else {
            resResult.setCode(400);
            resResult.setMsg("添加失败");
            resResult.setCount(0);
            resResult.setData(null);
        }
        return resResult;
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<FootNav> del(@RequestParam("id") Integer id){
        ResResult<FootNav> resResult = new ResResult<>();
        FootNav footNav = footNavService.getFootNavById(id);
        if (footNav != null){
            int i = footNavService.del(id);
            resResult.setData(footNav);
            resResult.setCount(i);
            resResult.setCode(200);
            resResult.setMsg("删除成功");
        }else{
            resResult.setCode(400);
            resResult.setMsg("删除失败");
            resResult.setCount(0);
            resResult.setData(null);
        }
        return resResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<FootNav> update(@RequestBody FootNav footNav){
        ResResult<FootNav> resResult = new ResResult<>();
        if (footNav!=null){
            int i = footNavService.update(footNav);
            resResult.setCode(200);
            resResult.setMsg("修改成功");
            resResult.setCount(i);
            resResult.setData(footNav);
        }else {
            resResult.setData(null);
            resResult.setMsg("修改失败");
            resResult.setCount(0);
            resResult.setCode(400);
        }
        return resResult;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<FootNav>> all(){
        ResResult<List<FootNav>> resResult = new ResResult<>();
        List<FootNav> list = footNavService.listAll();
        resResult.setCode(0);
        resResult.setCount(list.size());
        resResult.setData(list);
        return resResult;
    }

    @RequestMapping(value = "/addFootNav",method = RequestMethod.GET)
    public String addFootNav(){
        return "foot/addFootNav";
    }

    @RequestMapping(value = "/updateFootNav",method = RequestMethod.GET)
    public String addFootNav(@RequestParam("id") Integer id, Model model){
        FootNav footNav = footNavService.getFootNavById(id);
        model.addAttribute("footNav",footNav);
        return "foot/upFootNav";
    }


}
