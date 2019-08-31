package problog.service;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import problog.entity.Article.ArticleContent;
import problog.mapper.Article.ArticleContentMapper;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
@Transactional
public class ArticleContentService {
    @Resource
    ArticleContentMapper articleContentMapper;

    //添加新的文章
    public int addNewArticleContent(ArticleContent articleContent){
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //设置创建发表日期
        //更新
        ArticleContent article = new ArticleContent();

        article.getArticleId();
        article.getContext();
        article.getIsTop();
        article.setModifiedByDate(timestamp);
        article.setCreateByDate(timestamp);
        int i = articleContentMapper.insert(article);
        return i;}

}
