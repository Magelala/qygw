package problog.service;

import problog.entity.Article.ArticleContent;
import problog.entity.Article.ArticleList;

import java.util.List;


public interface ArticleService {

    //增添新文章
    ArticleContent addNewArticle(ArticleContent articleContent);

    //显示列表
     List<ArticleContent> showArticle();

    List<ArticleContent> getArticleById(String title);
}
