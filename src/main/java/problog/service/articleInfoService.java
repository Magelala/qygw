package problog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Article.ArticleInfo;
import problog.mapper.Article.ArticleInfoMapper;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@Service
@Transactional
public class articleInfoService {
    @Resource
    ArticleInfoMapper articleInfoMapper;

    //添加新的文章
    public int addNewArticleInfo(ArticleInfo ArticleInfo){
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
        return i;
    }

    //删除文章
    public int deleteArticleInfo(int id) {
        return articleInfoMapper.deleteById(id);
    }

    //更新文章
    public int updateArticleInfo(ArticleInfo articleInfo){
        return articleInfoMapper.updateById(articleInfo);
    }

    //查询所有文章
    public List<ArticleInfo> getAllArticleInfo(){
        return articleInfoMapper.selectList(new Wrapper<ArticleInfo>() {
            @Override
            public ArticleInfo getEntity() {
                return new ArticleInfo();
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });
    }

    //查询单篇文章
    public ArticleInfo getArticleInfoById(int id){
        return articleInfoMapper.selectById(id);
    }
}
