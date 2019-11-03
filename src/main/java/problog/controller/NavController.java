package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.nav.Nav;
import problog.entity.response.ResResult;
import problog.service.NavService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/19 14:47
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;

    @Autowired
    private HttpServletRequest request;

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

    @RequestMapping(value = "/deletes",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<List<Nav>> deletes(@RequestParam("ids") Integer[] ids){
        ResResult<List<Nav>> resResult = new ResResult<>();
        List<Nav> list = new ArrayList<>();
        for(int id:ids){
            Nav nav = navService.getById(id);
            if (null != nav){
                list.add(nav);
            }
        }
        int i = navService.deletes(ids);
        resResult.setCount(i);
        resResult.setData(list);
        resResult.setCode(200);
        return resResult;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Nav> addParent(@RequestBody Nav nav){
        ResResult<Nav> resResult = new ResResult<>();
        nav.setAuthorId(1);
        nav.setPid(0);
        Integer max = navService.max();
        if (max==null){
            max=0;
        }
        nav.setSort(max+1);
        nav.setCreateTime(new Timestamp(System.currentTimeMillis()));
        nav.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = navService.addParentNav(nav);
        resResult.setCount(i);
        resResult.setCode(200);
        resResult.setMsg("添加父导航成功");
        resResult.setData(nav);
        return resResult;
    }

    @RequestMapping(value = "/addSub",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Nav> addSub(@RequestBody Nav nav,@RequestParam("pid") Integer pid){
        ResResult<Nav> resResult = new ResResult<>();
        Integer max = navService.maxSub(pid);
        if (max == null){
            max = 0;
        }
        System.out.println(max);
        nav.setSort(max+1);
        nav.setAuthorId(1);
        nav.setPid(pid);
        nav.setCreateTime(new Timestamp(System.currentTimeMillis()));
        nav.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = navService.addParentNav(nav);
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
            nav.setUpdateTime(new Timestamp(System.currentTimeMillis()));
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


    @RequestMapping(value = "/move",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Nav>> move(@RequestParam("currSort") Integer currSort,@RequestParam("id") Integer id,@RequestParam("operate") String operate){
        List<Nav> list = new ArrayList<>();
        ResResult<List<Nav>> resResult = new ResResult<>();
        Nav curr = navService.getById(id);
        list.add(curr);
        if ("up".equals(operate)){
            Nav prev = navService.up(currSort);
            if (null == prev){
                resResult.setMsg("前面已经没有数据了");
            }else {
                navService.updateSelf(id,prev.getSort());
                navService.updateSelf(prev.getId(),currSort);
                list.add(prev);
            }
        }else if("down".equals(operate)){
            Nav next = navService.down(currSort);
            if (null == next){
                resResult.setMsg("后面已经没有数据了");
            }else{
                navService.updateSelf(id,next.getSort());
                navService.updateSelf(next.getId(),currSort);
                list.add(next);
            }
        }
        resResult.setData(list);
        return resResult;
    }

    @RequestMapping(value = "/moveSub",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Nav>> moveSub(@RequestParam("currSort") Integer currSort,
                                        @RequestParam("id") Integer id,
                                        @RequestParam("pid") Integer pid,
                                        @RequestParam("operate") String operate){
        List<Nav> list = new ArrayList<>();
        ResResult<List<Nav>> resResult = new ResResult<>();
        Nav curr = navService.getById(id);
        list.add(curr);
        if ("up".equals(operate)){
            Nav prev = navService.upSub(currSort,pid);
            if (prev != null){
                navService.updateSubSelf(id,prev.getSort(),pid);
                navService.updateSubSelf(prev.getId(),currSort,pid);
                list.add(prev);
            }else{
                resResult.setMsg("前面没有数据了");
            }
        }else if("down".equals(operate)){
            Nav next = navService.downSub(currSort,pid);
            if (next != null){
                navService.updateSubSelf(id,next.getSort(),pid);
                navService.updateSubSelf(next.getId(),currSort,pid);
                list.add(next);
            }else{
                resResult.setMsg("后面没有数据了");
            }
        }
        resResult.setData(list);
        return resResult;
    }


    @RequestMapping(value = "/navSub",method = RequestMethod.GET)
    public String navSub(@RequestParam("pid") Integer pid, Model model){
        model.addAttribute("pid",pid);
        model.addAttribute("title",navService.selectTitle(pid));
        return "nav/subNav";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String navParent(){
        return "nav/nav";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addParent(){
        return "nav/addParent";
    }

    @RequestMapping(value = "/addSub",method = RequestMethod.GET)
    public String addSub(@RequestParam("pid") Integer pid,Model model){
        model.addAttribute("pid",pid);
        return "nav/addSub";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id") Integer id, Model model){
        Nav nav = navService.getById(id);
        model.addAttribute("navs",nav);
        return "nav/editParent";
    }

    @RequestMapping(value = "/editSub",method = RequestMethod.GET)
    public String editSub(@RequestParam("id") Integer id,@RequestParam("pid") Integer pid, Model model){
        Nav nav = navService.getById(id);
        model.addAttribute("navs",nav);
        model.addAttribute("pNav",navService.getById(pid));
        return "nav/editSub";
    }

}