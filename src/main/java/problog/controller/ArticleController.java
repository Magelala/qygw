package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import problog.entity.Article.ArticleContent;

import problog.service.ArticleContentService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;

@Controller
@RequestMapping("/article/writeArticle")
public class ArticleController {
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


    /**
     //进入上传文件表单页面
     @RequestMapping("/registerForm")
     public String registerForm(){
     return "registerForm";
     }
     */

    @PostMapping(value = "/upload")
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file) throws Exception{
        //接收参数description,file
        System.out.println("description="+description);
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()){
            //上传文件路径
            String path = request.getServletContext().getRealPath("/upload/");
            System.out.println("path = "+path);
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdir();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path+File.separator + filename));
            return "success";
        }else {
            return "error";

        }}
}
