package problog.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.Article.ArticleContent;
import problog.entity.Category.Category;
import problog.entity.carousel.Carousel;
import problog.entity.response.ResResult;
import problog.mapper.Article.ArticleContentMapper;

import problog.mapper.Category.CategoryMapper;
import problog.service.ArticleService;
import problog.service.CategoryService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

import java.util.List;


@Controller
@RequestMapping("/article")
@Api(value = "文章信息接口",tags = {"用户增删改查文章的接口"})
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private ArticleContentMapper articleContentMapper;

    @Resource
    CategoryService categoryService;





    @ApiOperation(value = "添加文章",notes = "添加一篇文章")
    @ApiResponses({
            @ApiResponse(code=200,message = "success"),
            @ApiResponse(code=400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    //保存新的文章，插入一条数据
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public ResResult<ArticleContent> addNewArticle(@RequestBody @ApiParam(name = "文章信息对象",value = "文章信息的JSON对象",required = true) ArticleContent articleContent) {
        ResResult<ArticleContent> resResult = new ResResult<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        articleContent.setModifiedByDate(timestamp);
        articleContent.setCreateByDate(timestamp);
        String path = (String) request.getSession().getAttribute("path");
        articleContent.setPicture(path);
        request.getSession().setAttribute("path",null);
        articleContent.setIsTop("0");
        int i = articleService.addNewArticle(articleContent);
        resResult.setData(articleContent);
        resResult.setCode(200);
        resResult.setMsg("添加成功");
        resResult.setCount(i);
        return resResult;
    }

    //显示全部文章列表
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<ArticleContent>> texts(@RequestParam(value = "limit",required = false) Integer limit,
                                                 @RequestParam(value = "page",required = false) Integer page){
        //分页查询文章
        List<ArticleContent> list = articleContentMapper.showPage(limit*(page-1),limit);
        //查询文章总数
        List<ArticleContent> all = articleService.showArticle();
        ResResult<List<ArticleContent>> result = new ResResult<>();
        result.setCode(0);
        result.setLimit(limit);
        result.setPage(page);
        result.setCount(all.size()); //设置总数
        result.setData(list);
        return result;
    }

    //显示全部category列表
    @RequestMapping(value = "/showCategory",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Category>> allCategory(){
        //查询分类总数
        List<Category> categoryList = categoryService.showCategory();
        ResResult<List<Category>> result = new ResResult<>();
        result.setCode(0);
//        result.setCount(categoryList.size()); //设置总数
        result.setData(categoryList);
        return result;
    }


    //------------------------文章页面的请求,返回一个页面--------------------------------


    @RequestMapping(value = "/finds",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Carousel>> findByTitle(@RequestParam(value = "title") String title,
                                                 @RequestParam("limit")int limit,
                                                 @RequestParam("page") int page){
        //int end = title.indexOf(",");
        List<Carousel> all = articleContentMapper.selectTitle(title.trim());
        List<Carousel> list = articleService.getCarouselByTitle(title.trim(),(page-1)*limit,limit);
        ResResult<List<Carousel>> resResult = new ResResult<>();
        resResult.setCode(0);
        resResult.setCount(all.size());
        resResult.setPage(page);
        resResult.setData(list);
        resResult.setMsg("成功");
        resResult.setLimit(limit);
        return resResult;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String showArticle(){
        return "article/article";
    }


    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String WriteArticle(Model model){
        List<Category> category = categoryService.showCategory();
        model.addAttribute("category",category);
        return "article/writeArticle";

    }

    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public String showCategory(){
        return "article/category";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public void deleteArticle(@PathVariable Integer id) {
        int i = articleService.deleteArticle(id);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id, Model model){
        ArticleContent byId = articleService.getById(id);
        model.addAttribute("edit",byId);
        return "article/update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<ArticleContent> update(@RequestBody ArticleContent articleContent){
        ResResult<ArticleContent> resResult = new ResResult<>();
        ArticleContent articleContent11 = articleService.getById(articleContent.getId());
        if (null != articleContent11){
            String paths = (String)request.getSession().getAttribute("path");
            resResult.setCode(0);
            resResult.setMsg("修改成功");
            articleContent.setPicture(paths);
            int i = articleService.update(articleContent);
            resResult.setCount(i);
            resResult.setData(articleContent);
            request.getSession().setAttribute("path",null);
        }else{
            resResult.setCode(-1);
            resResult.setMsg("修改失败");
            resResult.setData(null);
        }
        return resResult;
    }
}