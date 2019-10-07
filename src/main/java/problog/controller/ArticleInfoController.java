package problog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import problog.entity.Article.ArticleInfo;
import problog.entity.response.RespBean;
import problog.service.articleInfoService;

import java.util.List;

@RestController
@RequestMapping(value = "/article")
@Api(value = "文章信息接口",tags = {"用户增删改查文章的接口"})
public class ArticleInfoController {

    @Autowired
    private articleInfoService articleInfoService;

    @ApiOperation(value = "添加文章",notes = "添加一篇文章")
    @ApiResponses({
            @ApiResponse(code=200,message = "success"),
            @ApiResponse(code=400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @PostMapping(value = "/add")
    public RespBean<ArticleInfo> addNewArticleInfo(@RequestBody @ApiParam(name = "文章信息对象",value = "文章信息的JSON对象",required = true) ArticleInfo articleInfo){
        //在数据库中查询新添加的文章是否存在,不存在添加；存在则不添加,这里比较两个文章对象采用的是比较它们的id
        ArticleInfo newArticleInfo = articleInfoService.getArticleInfoById(articleInfo.getId());
        if (null == newArticleInfo){
            articleInfoService.addNewArticleInfo(articleInfo);
            RespBean<ArticleInfo> respBean = new RespBean<ArticleInfo>(true, articleInfo, 200);
            return respBean;
        }else{
            return new RespBean<ArticleInfo>(false,"你添加的文章已经存在,请重新添加！");
        }
    }

    @ApiOperation(value = "删除文章",tags = {"根据文章的id来删除文章"})
    @ApiImplicitParam(name = "id",value = "文章id",required = true,paramType = "query",dataType = "int")
    @ApiResponses({
            @ApiResponse(code= 200,message = "success"),
            @ApiResponse(code= 400,message = "参数错误"),
            @ApiResponse(code = 401,message = "未授权"),
            @ApiResponse(code = 404,message = "页面未找到"),
            @ApiResponse(code = 403,message = "出错了！")
    })
    @DeleteMapping("/delete")
    public RespBean<ArticleInfo> deleteArticleInfo(@RequestParam(value = "id") int id){
        ArticleInfo newArticleInfo = articleInfoService.getArticleInfoById(id);
        if(null != newArticleInfo){
            articleInfoService.deleteArticleInfo(id);
            RespBean<ArticleInfo> respBean = new RespBean<>(true,newArticleInfo,200);
            return respBean;
        }else{
            return new RespBean<ArticleInfo>(false,"删除失败，该文章不存在！");
        }
    }

    @ApiOperation(value = "修改文章",tags = "修改文章")
    @PutMapping("/update")
    public RespBean<ArticleInfo> updateArticleInfo(@RequestBody @ApiParam(name = "文章信息对象",value = "文章信息对象的JSON对象",required = true) ArticleInfo articleInfo){
        ArticleInfo articleInfo1 = articleInfoService.getArticleInfoById(articleInfo.getId());
        if (null != articleInfo1){
            articleInfoService.updateArticleInfo(articleInfo);
            return new RespBean<>(true,articleInfo,200);
        }else{
            return new RespBean<>(false,"修改失败,该文章不存在！");
        }
    }

    @ApiOperation(value = "获取文章信息",tags = "根据id来获取文章信息")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,paramType = "query",dataType = "int")
    @GetMapping("/findById")
    public RespBean<ArticleInfo> getArticleInfoById(@RequestParam(value = "id") int id){
        ArticleInfo articleInfo = articleInfoService.getArticleInfoById(id);
        if (null != articleInfo){
            return new RespBean<>(true,articleInfo,200);
        }else{
            return new RespBean<>(false,"该文章不存在！");
        }
    }

    @ApiOperation(value = "获取所有文章")
    @GetMapping("/all")
    public List<ArticleInfo> getAllArticleInfo(){
        return articleInfoService.getAllArticleInfo();
    }

}
