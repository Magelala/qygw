package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Article.ArticleContent;

import problog.mapper.Article.ArticleContentMapper;
import problog.service.ArticleService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleContentMapper articleContentMapper;


    @Override
    public int addNewArticle(ArticleContent articleContent) {
        return articleContentMapper.insert(articleContent);
    }

//    @Override
//    public List<ArticleContent> showArticle(){
//        return articleContentMapper.selectList(null);
//        return articleContentMapper.selectArticle();
//    }

    @Override
    public List<ArticleContent> all(int limit, int page) {
        return articleContentMapper.showPage(limit*(page-1),page);
    }

    @Override
    public ArticleContent getById(Integer id) {
        return articleContentMapper.selectById(id);
    }

    @Override
    public int update(ArticleContent articleContent) {
        return articleContentMapper.updateById(articleContent);
    }

    @Override
    public List<ArticleContent> getArticleByTitle(String title, int limit, int page) {
        return articleContentMapper.selectTitlePage(title,limit,page);
    }

    @Override
    public int articleCount() {
        return articleContentMapper.selectCount(null);
    }

    @Override
    public ArticleContent selectArticleById(Integer id) {
        return  articleContentMapper.getArticleById(id);
    }

//    @Override
//    public List<ArticleContent> getArticleByTitle(String title){
//        List<ArticleContent> list = articleContentMapper.selectList(new LambdaQueryWrapper<ArticleContent>().eq(ArticleContent::getTitle, title));
//        System.out.println(list);
//        return list;
//
//    }

    @Override
    public int deleteArticle(Integer id) {
        return articleContentMapper.deleteById(id);
    }

    @Override
    public int updateArticle(ArticleContent articleContent) {
        return articleContentMapper.updateById(articleContent);
    }

}

