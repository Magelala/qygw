package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Article.ArticleContent;

import problog.mapper.Article.ArticleContentMapper;
import problog.service.ArticleContentService;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
@Transactional
public class ArticleContentServiceImpl implements ArticleContentService {
    @Resource
    ArticleContentMapper articleContentMapper;



    @Override
    public ArticleContent addNewArticle(ArticleContent articleContent) {
        articleContentMapper.insert(articleContent);
        return articleContent;}
    }

