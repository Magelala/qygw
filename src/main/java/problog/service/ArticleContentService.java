package problog.service;

import problog.entity.Article.ArticleContent;

import java.util.List;


public interface ArticleContentService {

    //增添新文章
    ArticleContent addNewArticle(ArticleContent articleContent);

    //显示列表
     List<ArticleContent> showArticle();
}
