package problog.service;

import problog.entity.Article.ArticleContent;
import problog.entity.Category.Category;
import problog.entity.carousel.Carousel;

import java.util.List;


public interface ArticleService {

    //增添新文章,返回的是添加了几篇文章
    int addNewArticle(ArticleContent articleContent);

    //显示文章列表,返回所有文章列表
//    List<ArticleContent> showArticle();

//    //模糊搜索,返回一个文章对象
//    List<ArticleContent> getArticleByTitle(String title);

    //根据id删除一篇文章,返回删除了几篇文章
    int deleteArticle(Integer id);

    //更新一篇文章,返回更新了几篇文章
    int updateArticle(ArticleContent articleContent);

    List<ArticleContent> all(int limit,int page);


    ArticleContent getById(Integer id);

    //更新文章内容
    int update(ArticleContent articleContent);

    //根据title进行模糊查询
    List<ArticleContent> getArticleByTitle(String title, int limit, int page);

    //返回总记录条数
    int articleCount();

    ArticleContent selectArticleById(Integer id);

}
