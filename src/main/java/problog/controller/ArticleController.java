package problog.controller;


import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import problog.entity.Article.ArticleContent;

import problog.entity.response.ResResult;
import problog.entity.response.RespBean;
import problog.mapper.Article.ArticleContentMapper;
import problog.service.ArticleService;


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

    //------------------------文章页面的请求,返回一个页面--------------------------------


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String showArticle(){
        return "article/article";
    }


    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String WriteArticle(){
        return "article/writeArticle";
    }

}




