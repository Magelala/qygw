package problog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import problog.entity.response.ResResult;
import problog.entity.response.RespBean;
import problog.entity.carousel.Carousel;
import problog.mapper.carousel.CarouselMapper;
import problog.service.CarouselService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : shengjun
 * @Date : create in
 */
@Controller
@RequestMapping(value = "/advertisement")
@Api(value = "广告管理接口",tags = "动态修改轮播图")
public class CarouselController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CarouselService carouselService;

    @Resource
    private CarouselMapper carouselMapper;


    @GetMapping("/findPage")
    @ResponseBody
    public ResResult<List<Carousel>> findByPage(@RequestParam(value = "limit", required = false) Integer limit,
                                                @RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "id",required = false) String id){
        List<Carousel> carousels = carouselMapper.selectPage((page-1)*limit,limit);
        if (id==null || id.isEmpty()){
            List<Carousel> carouselList = carouselService.selectAllList();
            if (carousels != null){
                ResResult<List<Carousel>> resResult = new ResResult<>(0,"成功",carousels);
                resResult.setLimit(limit);
                resResult.setPage(page);
                resResult.setCount(carouselList.size());
                return resResult;
            }
        }else{
            List<Carousel> list = new ArrayList<>();
            ResResult<List<Carousel>> resResult = new ResResult<>(0,"成功",list);
            Carousel carousel = carouselService.selectCarouselById(Integer.valueOf(id));
            if (carousel!=null){
                list.add(carousel);
                resResult.setId(id);
                return resResult;
            }else{
                resResult.setId(id);
                return resResult;
            }
        }
        return null;
    }

    @ApiOperation(value = "获取所有的广告信息")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Carousel>> getAll(){
        List<Carousel> carousels = carouselService.selectAllList();
        ResResult<List<Carousel>> resResult = ResResult.ok(carousels);
        resResult.setCount(carousels.size());
        resResult.setCode(0);
        return resResult;
    }

    @ApiOperation(value = "获取某一张广告信息",notes ="根据id来获取广告")
    @ApiImplicitParam(name = "id",value = "广告id",required = true,paramType = "query",dataType = "int")
    @GetMapping("/find")
    @ResponseBody
    public ResResult<Carousel> getCarouselById(@RequestParam(value = "id",required = true) Integer id){
        Carousel carousel = carouselService.selectCarouselById(id);
        if (null != carousel){
            ResResult<Carousel> resResult = new ResResult<>();
            resResult.setCode(0);
            resResult.setData(carousel);
            resResult.setCount(1);
            return resResult;
        }else {
            return ResResult.fail(-1);
        }
    }

    @ApiOperation(value = "获取广告信息",notes = "根据标题来获取广告信息")
    @ApiImplicitParam(name = "title",value = "广告标题",required = true,paramType = "query",dataType = "String")
    @RequestMapping(value = "/findByTitle",method = RequestMethod.GET)
    @ResponseBody
    public RespBean<Carousel> getCarouselByTitle(@RequestParam("title") String title){
        Carousel carousel = carouselService.selectCarouselByTitle(title);
        if (null != carousel){
            return new RespBean<>(true,carousel,200);
        }else{
            return new RespBean<>(false,"不存在该广告标题");
        }
    }

    @ApiOperation(value = "添加一则广告",notes = "添加广告")
    @ApiResponses({
            @ApiResponse(code=200,message = "success"),
            @ApiResponse(code=400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public RespBean<Carousel> createCarousel(@RequestBody @ApiParam(name = "广告对象",value = "传入JSON广告对象") Carousel carousel){
        Carousel carousel1 = carouselService.selectCarouselById(carousel.getId());
        if (null == carousel1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            carousel.setCreateDate(timestamp);
            String path = (String)request.getSession().getAttribute("path");
            carousel.setImgUrl(path);
            carouselService.insertCarousel(carousel);
            return new RespBean<>(true,carousel,200);
        }else{
            return new RespBean<>(false,"你添加的广告已经存在,请重新添加！");
        }
    }


    @ApiOperation(value = "修改广告",notes = "根据id查询出广告后，进行修改")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public RespBean<Carousel> update(@RequestBody @ApiParam(name = "广告对象",value = "传入广告的JSON对象") Carousel carousel){
        Carousel carousel1 =carouselService.selectCarouselById(carousel.getId());
        if (null != carousel){
            carouselService.updateCarouselById(carousel);
            return new RespBean<>(true,carousel,200);
        }else {
            return new RespBean<>(false,"修改失败,该广告不存在！");
        }
    }


    @ApiOperation(value = "删除广告",notes = "根据广告的id来删除")
    @ApiImplicitParam(name = "id",value = "广告id",required = true,paramType = "query",dataType = "int")
    @ApiResponses({
            @ApiResponse(code= 200,message = "success"),
            @ApiResponse(code= 400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Carousel>  deleteCarouselById(@RequestParam("id") Integer id){
        Carousel carousel = carouselService.selectCarouselById(id);
        if(null != carousel){
            carouselService.deleteCarouselById(id);
            ResResult<Carousel> resResult = new ResResult<>();
            resResult.setCode(0);
            resResult.setData(carousel);
            return resResult;
        }else{
            return ResResult.fail(-1);
        }
    }

    //上移
//    @RequestMapping(value = "/updateSort",method = RequestMethod.GET)
//    public void updateSort(){
//        carouselService.moveUp(2);
////        if (s.equals("down")){
////            carouselService.moveDown(id);
////        }else if(s.equals("up")){
////            carouselService.moveUp(id);
////        }
//    }


}
