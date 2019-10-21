package problog.mapper.Article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.Article.ArticleContent;
import problog.entity.carousel.Carousel;

import java.util.List;

public interface ArticleContentMapper extends BaseMapper<ArticleContent> {

    @Select("select * from article_content order by sort asc limit #{limit},#{page}")
    List<ArticleContent> showPage(@Param("limit") int limit,@Param("page") int page);

    //按照标题进行模糊搜索并且分页
    @Select("select * from article_content where title like concat('%',#{title},'%') order by sort asc limit #{limit},#{page}")
    List<Carousel> selectTitlePage(@Param("title") String title, @Param("limit") int limit, @Param("page") int page);

    //按照标题进行模糊搜索
    @Select("select * from article_content where title like concat('%',#{title},'%')")
    List<Carousel> selectTitle(@Param("title") String title);

}
