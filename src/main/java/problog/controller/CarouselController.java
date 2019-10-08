package problog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.carousel.Carousel;
import problog.entity.response.ResResult;
import problog.mapper.carousel.CarouselMapper;
import problog.service.CarouselService;
import springfox.documentation.annotations.ApiIgnore;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/advertise")
@Api(value = "广告管理接口",tags = "动态轮播")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private CarouselMapper carouselMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Carousel>> all(@RequestParam(value = "limit", required = false) Integer limit,
                                         @RequestParam(value = "page", required = false) Integer page){
        List<Carousel> carousels = carouselMapper.selectPage((page-1)*limit,limit);
        List<Carousel> carouselList = carouselService.all();
        ResResult<List<Carousel>> resResult = new ResResult<>(0,"成功",carousels);
        resResult.setLimit(limit);
        resResult.setPage(page);
        resResult.setCount(carouselList.size());
        return resResult;
    }

    @ApiOperation(value = "添加广告")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Carousel> add(@RequestBody @ApiParam(name = "广告对象",value = "JSON对象") Carousel carousel){
        ResResult<Carousel> carouselResResult = new ResResult<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        carousel.setCreateDate(timestamp);
        String path = (String)request.getSession().getAttribute("path");
        carousel.setImgUrl(path);
        int max = carouselService.max();
        carousel.setSort(max+1);
        int count = carouselService.save(carousel);
        carouselResResult.setData(carousel);
        carouselResResult.setCode(200);
        carouselResResult.setCount(count);
        carouselResResult.setMsg("添加成功");
        return carouselResResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Carousel> delete(@RequestParam("id") Integer id){
        Carousel carousel = carouselService.getById(id);
        ResResult<Carousel> resResult = new ResResult<>();
        if (null != carousel){
            carouselService.delete(id);
            resResult.setCount(200);
            resResult.setData(carousel);
            resResult.setMsg("删除成功");
        }else{
            resResult.setCode(-1);
            resResult.setData(null);
            resResult.setMsg("删除失败");
        }
        return resResult;
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Carousel>> findById(@RequestParam(value = "id",required = true) Integer id){
        Carousel carousel = carouselService.getById(id);
        List<Carousel> list = new ArrayList<>();
        ResResult<List<Carousel>> resResult = new ResResult<>();
        if (null != carousel){
            resResult.setCode(0);
            resResult.setCount(1);
            list.add(carousel);
            resResult.setData(list);
        }else{
            resResult.setCount(0);
            resResult.setData(null);
        }
        return resResult;
    }

    @ApiOperation(value = "模糊查询",notes = "根据标题查询广告")
    @RequestMapping(value = "/finds",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Carousel>> findByTitle(@RequestParam(value = "title") String title,
                                                 @RequestParam("limit")int limit,
                                                 @RequestParam("page") int page){
        //int end = title.indexOf(",");
        List<Carousel> all = carouselMapper.selectTitle(title.trim());
        List<Carousel> list = carouselService.getCarouselByTitle(title.trim(),(page-1)*limit,limit);
        ResResult<List<Carousel>> resResult = new ResResult<>();
        resResult.setCode(0);
        resResult.setCount(all.size());
        resResult.setPage(page);
        resResult.setData(list);
        resResult.setMsg("成功");
        resResult.setLimit(limit);
        return resResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Carousel> update(@RequestBody Carousel carousel){
        ResResult<Carousel> resResult = new ResResult<>();
        Carousel carousel1 = carouselService.getById(carousel.getId());
        if (null != carousel1){
            String path = (String)request.getSession().getAttribute("path");
            resResult.setCode(0);
            resResult.setMsg("修改成功");
            carousel.setImgUrl(path);
            carouselService.update(carousel);
            resResult.setData(carousel);
            request.getSession().setAttribute("path",null);
        }else{
            resResult.setCode(-1);
            resResult.setMsg("修改失败");
            resResult.setData(null);
        }
        return resResult;
    }

    /**
     * 上移：取上一条记录排序号，将当前记录与上一条记录排序号调换位置
     * 下移：取下一条记录排序号，将当前记录与下一条记录排序号调换位置
     * @param currSort 当前排序
     * @param operate 上移还是下移
     * @return
     */
    @RequestMapping(value = "move",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Carousel>> move(@RequestParam("currSort") Integer currSort,@RequestParam("id") Integer id,@RequestParam("operate") String operate){
        List<Carousel> carousels = new ArrayList<>();
        ResResult<List<Carousel>> resResult = new ResResult<>();
        Carousel curr = carouselService.getById(id);
        carousels.add(curr);
        if ("up".equals(operate)){
            Carousel prev = carouselService.up(currSort);
            carouselService.updateSelfSort(id,prev.getSort());
            carouselService.updateSelfSort(prev.getId(),currSort);
            carousels.add(prev);
        }else if("down".equals(operate)){
            Carousel next = carouselService.down(currSort);
            carouselService.updateSelfSort(id,next.getSort());
            carouselService.updateSelfSort(next.getId(),currSort);
            carousels.add(next);
        }
        resResult.setData(carousels);
        return resResult;
    }

    //----------------------------------------批量删除需求未完善--------------------------------------------

    @RequestMapping(value = "deletes",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<List<Carousel>> batchDelete(@RequestParam("ids") Integer[] ids){
        List<Carousel> list = new ArrayList<>();
        ResResult<List<Carousel>> resResult = new ResResult<>();
        for (Integer id:ids){
            Carousel carousel = carouselService.getById(id);
            if (null != carousel){
                list.add(carousel);
            }
        }
        int i = carouselMapper.deleteList(ids);
        resResult.setCount(i);
        resResult.setCode(0);
        resResult.setData(list);
        return resResult;
    }

    //-------------------------------------------------------------------------------------------------

    @ApiIgnore
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "carousel/add";
    }

    @ApiIgnore
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id, Model model){
        Carousel carousel = carouselService.getById(id);
        model.addAttribute("carousel",carousel);
        return "carousel/update";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ApiIgnore
    public String carousel(){
        return "carousel";
    }

}
