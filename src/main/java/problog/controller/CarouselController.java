package problog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import problog.entity.RespBean;
import problog.entity.carousel.Carousel;
import problog.service.CarouselService;

import java.util.Date;
import java.util.List;

/**
 * @Author : shengjun
 * @Date : create in
 */
@RestController
@RequestMapping(value = "/advertisement")
@Api(value = "广告管理接口",tags = "动态修改轮播图")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "获取所有的广告信息")
    @GetMapping("/all")
    public List<Carousel> getAll(){
        return carouselService.selectAllList();
    }

    @ApiOperation(value = "获取某一张广告信息",tags = {"根据id来获取广告"})
    @ApiImplicitParam(name = "id",value = "广告id",required = true,paramType = "query",dataType = "int")
    @GetMapping("/findById")
    public RespBean<Carousel> getCarouselById(@RequestParam("id") Integer id){
        Carousel carousel = carouselService.selectCarouselById(id);
        if (null != carousel){
            return new RespBean<>(true,carousel,200);
        }else {
            return new RespBean<>(false,"该广告不存在！");
        }
    }

    @ApiOperation(value = "获取广告信息",tags = "根据标题来获取广告信息")
    @ApiImplicitParam(name = "title",value = "广告标题",required = true,paramType = "query",dataType = "String")
    @GetMapping("/findByTitle")
    public RespBean<Carousel> getCarouselByTitle(@RequestParam("title") String title){
        Carousel carousel = carouselService.selectCarouselByTitle(title);
        if (null != carousel){
            return new RespBean<>(true,carousel,200);
        }else{
            return new RespBean<>(false,"不存在该广告标题");
        }
    }

    @ApiOperation(value = "添加一则广告",tags = "添加广告")
    @ApiResponses({
            @ApiResponse(code=200,message = "success"),
            @ApiResponse(code=400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @PostMapping("/add")
    public RespBean<Carousel> createCarousel(@RequestBody @ApiParam(name = "广告对象",value = "传入JSON广告对象") Carousel carousel){
        Carousel carousel1 = carouselService.selectCarouselById(carousel.getId());
        if (null == carousel1){
            carouselService.insertCarousel(carousel);
            return new RespBean<>(true,carousel,200);
        }else{
            return new RespBean<>(false,"你添加的广告已经存在,请重新添加！");
        }
    }


    @ApiOperation(value = "修改广告",tags = "根据id查询出广告后，进行修改")
    @PutMapping("/update")
    public RespBean<Carousel> update(@RequestBody @ApiParam(name = "广告对象",value = "传入广告的JSON对象") Carousel carousel){
        Carousel carousel1 =carouselService.selectCarouselById(carousel.getId());
        if (null != carousel){
            carouselService.updateCarouselById(carousel);
            return new RespBean<>(true,carousel,200);
        }else {
            return new RespBean<>(false,"修改失败,该广告不存在！");
        }
    }


    @ApiOperation(value = "删除广告",tags = {"根据广告的id来删除"})
    @ApiImplicitParam(name = "id",value = "广告id",required = true,paramType = "query",dataType = "int")
    @ApiResponses({
            @ApiResponse(code= 200,message = "success"),
            @ApiResponse(code= 400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @DeleteMapping("/delete")
    public RespBean<Carousel>  deleteCarouselById(@RequestParam("id") Integer id){
        Carousel carousel = carouselService.selectCarouselById(id);
        if(null != carousel){
            carouselService.deleteCarouselById(id);
            return new RespBean<>(true,carousel,200);
        }else{
            return new RespBean<>(false,"删除失败，该广告不存在！");
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
