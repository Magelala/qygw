package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import problog.entity.Article.ArticleInfo;
import problog.entity.RespBean;
import problog.service.articleInfoService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/article")
public class ArticleInfoController {
    @Resource
    articleInfoService articleInfoService;
    
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBean addNewarticleInfo(ArticleInfo articleInfo){
        int result = articleInfoService.addNewarticleInfo(articleInfo);
        if (result == 1){
            return new RespBean("success",articleInfo.getId()+ " ");
        }else {
            return new RespBean("error","文章保存失败");
        }}
}
