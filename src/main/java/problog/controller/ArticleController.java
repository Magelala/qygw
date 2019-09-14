package problog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import problog.entity.Article.ArticleContent;

import problog.service.ArticleContentService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleContentService articleContentService;


    //保存新的文章，插入一条数据
    @RequestMapping(value = "/writeArticle/add" ,method = RequestMethod.POST)
    @ResponseBody
    public ArticleContent addNewArticle(ArticleContent articleContent) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        articleContent.setModifiedByDate(timestamp);
        articleContent.setCreateByDate(timestamp);
        articleContentService.addNewArticle(articleContent);
        return articleContent;
    }


    private static final Logger log = LoggerFactory.getLogger(ArticleContent.class);
    @RequestMapping(value = "/writeArticle/upload" ,method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file",required = false) MultipartFile file){
        try{
//            用于获取项目根目录
            File f = new File("");
            String filePath = f.getCanonicalPath()+"\\src\\main\\resources\\static\\upload\\picture\\";
            System.out.println(filePath);
            if (file.isEmpty()){
                return "文件夹为空";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为："+fileName);
            //设置文件存储路径
            String path = filePath + fileName;
            File dest = new File(path);
            //检测是否存在目录
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs(); //新建文件夹
            }
            file.transferTo(dest); //文件写入
            System.out.println("这是文件的路径"+path);
            ArticleContent content = new ArticleContent();
            String substring = path.substring(path.length()-fileName.length()-23);
            ModelMap map = new ModelMap();
            map.put("substring",substring);
//            content.setCover(substring);
            System.out.println(substring);
            return substring;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";

    }

    @PostMapping(value = "/articleList")
    @ResponseBody
    public List<ArticleContent> showArticle(){
        List<ArticleContent> list = articleContentService.showArticle();
        return list;
    }


}
