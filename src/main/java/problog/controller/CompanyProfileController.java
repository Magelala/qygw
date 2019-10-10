package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.carousel.CompanyProfile;
import problog.entity.response.ResResult;
import problog.mapper.carousel.CompanyProfileMapper;
import problog.service.CompanyProfileService;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/10 0:08
 */
@Controller
@RequestMapping("/companyProfile")
public class CompanyProfileController {

    @Autowired
    private CompanyProfileService companyProfileService;

    @Autowired
    private HttpServletRequest request;


    @Resource
    private CompanyProfileMapper companyProfileMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<CompanyProfile>> all(@RequestParam(value = "limit", required = false) Integer limit,
                                               @RequestParam(value = "page", required = false) Integer page){
        ResResult<List<CompanyProfile>> resResult = new ResResult<>();
        List<CompanyProfile> result  = companyProfileMapper.page((page-1)*limit,limit);
        List<CompanyProfile> list = companyProfileService.all();
        resResult.setCount(list.size());
        resResult.setCode(0);
        resResult.setLimit(limit);
        resResult.setPage(page);
        resResult.setData(result);
        return resResult;
    }

    @RequestMapping(value = "/find/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<CompanyProfile>> findById(@PathVariable(value = "id",required = true) Integer id){
        CompanyProfile companyProfile = companyProfileService.getById(id);
        List<CompanyProfile> list = new ArrayList<>();
        ResResult<List<CompanyProfile>> resResult = new ResResult<>();
        if (null != companyProfile){
            resResult.setCode(0);
            resResult.setCount(1);
            resResult.setData(list);
            list.add(companyProfile);
        }else{
            resResult.setCount(0);
            resResult.setData(null);
        }
        return resResult;
    }

    @RequestMapping(value = "/finds",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<CompanyProfile>> findsByTitle(@RequestParam(value = "title") String title,
                                                        @RequestParam("limit")int limit,
                                                        @RequestParam("page") int page){
        List<CompanyProfile> all = companyProfileMapper.title(title.trim());
        List<CompanyProfile> list = companyProfileMapper.titlePage(title.trim(),(page-1)*limit,limit);
        ResResult<List<CompanyProfile>> resResult = new ResResult<>();
        resResult.setCode(0);
        resResult.setCount(all.size());
        resResult.setData(list);
        resResult.setPage(page);
        resResult.setLimit(limit);
        return resResult;
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<CompanyProfile> delete(@RequestParam("id") Integer id){
        CompanyProfile companyProfile = companyProfileService.getById(id);
        ResResult<CompanyProfile> resResult = new ResResult<>();
        if (null != companyProfile){
            int i = companyProfileService.delete(id);
            resResult.setCount(i);
            resResult.setCode(200);
            resResult.setData(companyProfile);
            resResult.setMsg("删除成功!");
        }else{
            resResult.setCount(0);
            resResult.setData(null);
            resResult.setCode(-1);
            resResult.setMsg("删除失败!");
        }
        return resResult;
    }

    @RequestMapping(value = "/deletes",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<List<CompanyProfile>> deletes(@RequestParam("ids") Integer[] ids){
        List<CompanyProfile> list = new ArrayList<>();
        ResResult<List<CompanyProfile>> resResult = new ResResult<>();
        for (Integer id:ids){
            CompanyProfile companyProfile = companyProfileService.getById(id);
            if (null!=companyProfile){
                list.add(companyProfile);
            }
        }
        int i = companyProfileMapper.deleteList(ids);
        resResult.setCount(i);
        resResult.setData(list);
        resResult.setCode(0);
        return resResult;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<CompanyProfile> add(@RequestBody CompanyProfile companyProfile){
        ResResult<CompanyProfile> resResult = new ResResult<>();
        companyProfile.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String path = (String)request.getSession().getAttribute("path");
        companyProfile.setImgUrl(path);
        request.getSession().setAttribute("path",null);
        int max = companyProfileMapper.max();
        companyProfile.setSort(max+1);
        int i = companyProfileService.add(companyProfile);
        resResult.setCode(200);
        resResult.setData(companyProfile);
        resResult.setCount(i);
        resResult.setMsg("添加成功");
        return resResult;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<CompanyProfile> edit(@RequestBody CompanyProfile companyProfile){
        CompanyProfile companyProfile1 = companyProfileService.getById(companyProfile.getId());
        ResResult<CompanyProfile> resResult = new ResResult<>();
        if (null != companyProfile1){
            String path = (String)request.getSession().getAttribute("path");
            companyProfile.setImgUrl(path);
            request.getSession().setAttribute("path",null);
            int i = companyProfileService.update(companyProfile);
            resResult.setCount(i);
            resResult.setMsg("修改成功");
            resResult.setData(companyProfile);
            resResult.setCode(0);
        }else{
            resResult.setCount(0);
            resResult.setMsg("修改失败");
            resResult.setData(null);
            resResult.setCode(-1);
        }
        return resResult;
    }

    @RequestMapping(value = "/move",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<CompanyProfile>> move(@RequestParam("currSort") Integer currSort,@RequestParam("id") Integer id,@RequestParam("operate") String operate){
        List<CompanyProfile> list = new ArrayList<>();
        ResResult<List<CompanyProfile>> resResult = new ResResult<>();
        CompanyProfile curr = companyProfileService.getById(id);
        list.add(curr);
        if ("up".equals(operate)){
            CompanyProfile prev = companyProfileMapper.up(currSort);
            companyProfileMapper.updateSelfSort(id,prev.getSort());
            companyProfileMapper.updateSelfSort(prev.getId(),currSort);
            list.add(prev);
        }else if ("down".equals(operate)){
            CompanyProfile next = companyProfileMapper.down(currSort);
            companyProfileMapper.updateSelfSort(id,next.getSort());
            companyProfileMapper.updateSelfSort(next.getId(),currSort);
            list.add(next);
        }
        resResult.setCode(0);
        resResult.setData(list);
        return resResult;
    }


    @ApiIgnore
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "carousel/companyProfile";
    }

    @ApiIgnore
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "carousel/profileAdd";
    }

    @ApiIgnore
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id")Integer id, Model model){
        CompanyProfile companyProfile = companyProfileService.getById(id);
        model.addAttribute("companyProfile",companyProfile);
        return "carousel/profileEdit";
    }

}
