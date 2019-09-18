package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Article.ArticleContent;

import problog.entity.Article.ArticleList;
import problog.mapper.Article.ArticleContentMapper;
import problog.mapper.Article.ArticleListMapper;
import problog.service.ArticleService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleContentMapper articleContentMapper;

    @Resource
    ArticleListMapper articleListMapper;



    @Override
    public ArticleContent addNewArticle(ArticleContent articleContent) {
        articleContentMapper.insert(articleContent);
        return articleContent;}

        public List<ArticleContent> showArticle(){
            List<ArticleContent> list = articleContentMapper.selectList(null);
            return list;



        }

    }

