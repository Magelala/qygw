package problog.mapper.Article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.Article.ArticleContent;

import java.util.List;

public interface ArticleContentMapper extends BaseMapper<ArticleContent> {

    @Select("select * from article_content order by sort asc limit #{limit},#{page}")
    List<ArticleContent> showPage(@Param("limit") int limit,@Param("page") int page);

}
