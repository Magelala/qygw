package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import problog.entity.foot.CopyRight;
import problog.entity.foot.Link;
import problog.entity.response.ResResult;
import problog.service.FootService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:45
 */
@Controller
@RequestMapping("/foot")
public class FootController {

    @Autowired
    private FootService footService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Link>> allLink(){
        ResResult<List<Link>> resResult = new ResResult<>();
        List<Link> links = footService.allLink();
        resResult.setCode(0);
        resResult.setData(links);
        resResult.setCount(links.size());
        return resResult;
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Link> delLink(int id){
        ResResult<Link> resResult = new ResResult<>();
        Link link = footService.getLinkById(id);
        if (link != null){
            int i = footService.delLink(id);
            resResult.setCount(i);
            resResult.setMsg("删除成功");
            resResult.setData(link);
            resResult.setCode(200);
        }else{
            resResult.setCount(0);
            resResult.setCode(400);
        }
        return resResult;
    }

    @RequestMapping(value = "/updateL",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Link> updateLink(@RequestBody Link link){
        ResResult<Link> resResult = new ResResult<>();
        if (link!=null){
            int i = footService.updateLink(link);
            resResult.setCount(i);
            resResult.setMsg("修改成功");
            resResult.setCode(200);
        }else{
            resResult.setCount(0);
            resResult.setMsg("没有数据");
        }
        resResult.setData(link);
        return resResult;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Link> addLink(@RequestBody Link link){
        ResResult<Link> resResult = new ResResult<>();
        link.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int i = footService.addLink(link);
        resResult.setCode(200);
        resResult.setMsg("添加成功");
        resResult.setCount(i);
        resResult.setData(link);
        return resResult;
    }

    @RequestMapping(value = "/updateC",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<CopyRight> updateCopy(@RequestBody CopyRight copyRight){
        ResResult<CopyRight> resResult = new ResResult<>();
        if (copyRight!=null){
            int i = footService.updateCopyRight(copyRight);
            resResult.setCode(200);
            resResult.setData(copyRight);
            resResult.setMsg("修改成功!");
            resResult.setCount(i);
        }
        return resResult;
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("copy",footService.getCopyRightById(1));
        return "foot/foot";
    }
}
