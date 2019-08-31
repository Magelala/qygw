package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Article.ArticleInfo;
import problog.mapper.Article.ArticleInfoMapper;
import javax.annotation.Resource;
import java.sql.Timestamp;


@Service
@Transactional
public class articleInfoService {
    @Resource
    ArticleInfoMapper articleInfoMapper;

    //添加新的文章
    public int addNewarticleInfo(ArticleInfo ArticleInfo){
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //设置创建发表日期
        //更新
        ArticleInfo article = new ArticleInfo();
        article.getTitle();
        article.getSummary();
        article.getTraffic();
        article.setModifiedByDate(timestamp);
        article.setCreateByDate(timestamp);
        int i = articleInfoMapper.insert(article);
        return i;}
}
