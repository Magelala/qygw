package problog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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





    @Override
    public ArticleContent addNewArticle(ArticleContent articleContent) {
        articleContentMapper.insert(articleContent);
        return articleContent;}

        public List<ArticleContent> showArticle(){
            List<ArticleContent> list = articleContentMapper.selectList(null);
            return list;
        }
        public List<ArticleContent> getArticleById(String title){
            List<ArticleContent> list = articleContentMapper.selectList(new LambdaQueryWrapper<ArticleContent>().eq(ArticleContent::getTitle, title));
            System.out.println(list);
            return list;
        }

    }

