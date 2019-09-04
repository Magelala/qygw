package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import problog.entity.Article.ArticleContent;
import problog.entity.Article.ArticleInfo;
import problog.entity.RespBean;
import problog.service.ArticleContentService;
import problog.service.ArticleInfoService;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Controller
@RequestMapping("/article/writeArticle")
public class ArticleInfoController {
    @Resource
    ArticleContentService articleContentService;
    

    //保存新的文章，插入一条数据
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public ArticleContent addNewArticle(ArticleContent articleContent) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        articleContent.setModifiedByDate(timestamp);
        articleContent.setCreateByDate(timestamp);
        articleContentService.addNewArticle(articleContent);
        return articleContent;
    }
}
