package problog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import problog.entity.Article.ArticleContent;
import problog.entity.RespBean;
import problog.service.ArticleContentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/article")
public class ArticleContentController {
    @Resource
    ArticleContentService articleContentService;

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

        }
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public RespBean addNewArticleContent(ArticleContent articleContent){
        int result = articleContentService.addNewArticleContent(articleContent);
        if (result == 1){
            return new RespBean("success",articleContent.getId()+ " ");
        }else {
            return new RespBean("error","文章保存失败");
        }
    }



}
