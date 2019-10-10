package problog.controller;


import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import problog.entity.Article.ArticleContent;

import problog.entity.Article.ArticleList;
import problog.entity.response.RespBean;
import problog.service.ArticleService;
import problog.utils.FileUtils;


import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/article")
@Api(value = "文章信息接口",tags = {"用户增删改查文章的接口"})
public class ArticleController {

    @Resource
    ArticleService articleService;

    private static final Logger log = LoggerFactory.getLogger(ArticleContent.class);

    @ApiOperation(value = "添加文章",notes = "添加一篇文章")
    @ApiResponses({
            @ApiResponse(code=200,message = "success"),
            @ApiResponse(code=400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    //保存新的文章，插入一条数据
    @RequestMapping(value = "/writeArticle/add" ,method = RequestMethod.POST)
    @ResponseBody
    public RespBean<ArticleContent> addNewArticle(@RequestBody @ApiParam(name = "文章信息对象",value = "文章信息的JSON对象",required = true) ArticleContent articleContent) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        articleContent.setModifiedByDate(timestamp);
        articleContent.setCreateByDate(timestamp);
        articleService.addNewArticle(articleContent);
        return new RespBean<>(true,articleContent,200);
    }

    @GetMapping(value = "")
    public String showArticle(Model model){
        List<ArticleContent> list = articleService.showArticle();
        model.addAttribute("articleList",list);
        return "article";
    }

    @PostMapping(value = "")
    @ResponseBody
    public List<ArticleContent> getArticleById(@RequestParam(value="title",required=false) String title){
        List<ArticleContent> articleById = articleService.getArticleById(title);
        return articleById;
    }

    @GetMapping(value = "/show")
    @ResponseBody
    public List<ArticleContent> texts(){
        List<ArticleContent> list = articleService.showArticle();
        return list;
    }



}




