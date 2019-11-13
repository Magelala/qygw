package problog.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import problog.entity.Article.ArticleContent;
import problog.entity.Category.Category;
import problog.entity.response.ResResult;
import problog.mapper.Article.ArticleContentMapper;
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
        List<ArticleContent> list = articleContentMapper.selectArticle(limit*(page-1),limit);
        //查询文章总数
        int i = articleService.articleCount();
        ResResult<List<ArticleContent>> result = new ResResult<>();
        result.setCode(0);
        result.setLimit(limit);
        result.setPage(page);
        result.setCount(i); //设置总数
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
        result.setData(categoryList);
        return result;
    }

    @PostMapping("/addCategory")
    @ResponseBody
    public ResResult<Category> addCategory(@RequestBody Category category){
        ResResult<Category> CategoryResResult = new ResResult<>();
        int count = categoryService.addCategory(category);
        CategoryResResult.setData(category);
        CategoryResResult.setCode(200);
        CategoryResResult.setCount(count);
        CategoryResResult.setMsg("添加成功");
        return CategoryResResult;
    }

    @RequestMapping(value = "/finds",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<ArticleContent>> findByTitle(@RequestParam(value = "title") String title,
                                                 @RequestParam("limit")int limit,
                                                 @RequestParam("page") int page){
        //int end = title.indexOf(",");
        List<ArticleContent> all = articleContentMapper.selectTitle(title.trim());
        List<ArticleContent> list = articleService.getArticleByTitle(title.trim(),(page-1)*limit,limit);
        ResResult<List<ArticleContent>> resResult = new ResResult<>();
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


    //删除文章
    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public void deleteArticle(@PathVariable Integer id) {
         articleService.deleteArticle(id);
    }

    //删除分类
    @ResponseBody
    @PostMapping("deleteCategory/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }

    //编辑文章
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id, Model model){
        ArticleContent list = articleService.selectArticleById(id);
        model.addAttribute("edit",list);
        List<Category> category = categoryService.showCategory();
        model.addAttribute("category",category);
        return "article/update";
    }

    //编辑分类
    @RequestMapping(value = "/editCategory",method = RequestMethod.GET)
    public String updateCategory(@RequestParam("id") Integer id, Model model){
        Category list = categoryService.updateCategory(id);
        model.addAttribute("edit",list);
        return "article/updates";
    }

    //更新编辑文章
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<ArticleContent> update(@RequestBody ArticleContent articleContent){
        ResResult<ArticleContent> resResult = new ResResult<>();
        Integer i1 = articleService.articleCount();
        if (null != i1){
            resResult.setCode(0);
            resResult.setMsg("修改成功");
            int i = articleService.update(articleContent);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            articleContent.setModifiedByDate(timestamp);
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


    //更新分类
    @PutMapping("/updates")
    @ResponseBody
    public ResResult<Category> updates(@RequestBody Category category){
        ResResult<Category> resResult = new ResResult<>();
        Category category1 = categoryService.updateCategory(category.getId());
        if (null != category1){
            resResult.setCode(0);
            resResult.setMsg("修改成功");
            int i = categoryService.update(category);
            resResult.setCount(i);
            resResult.setData(category1);
            request.getSession().setAttribute("path",null);
            System.out.println("我被成功的更新了");
        }else{
            resResult.setCode(-1);
            resResult.setMsg("修改失败");
            resResult.setData(null);
            System.out.println("我被失败的更新了");
        }

        return resResult;
    }
}