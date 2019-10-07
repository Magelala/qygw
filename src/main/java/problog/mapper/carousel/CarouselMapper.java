package problog.mapper.carousel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import problog.entity.carousel.Carousel;

import java.util.List;

public interface CarouselMapper extends BaseMapper<Carousel> {

    //查询所有记录，按照sort字段顺序
    @Select("select * from carousel order by sort")
    List<Carousel> all();

    //分页查询，按照sort字段升序排序，根据两个请求参数指定，page:当前页,limit:限制一页显示多少条数据
    @Select("select * from carousel order by sort asc limit #{limit},#{page}")
    List<Carousel> selectPage(@Param("limit") int limit, @Param("page") int page);

    //按照标题进行模糊搜索
    @Select("select * from carousel where title like concat('%',#{title},'%')")
    List<Carousel> selectTitle(@Param("title") String title);

    //按照标题进行模糊搜索并且分页
    @Select("select * from carousel where title like concat('%',#{title},'%') order by sort asc limit #{limit},#{page}")
    List<Carousel> selectTitlePage(@Param("title") String title,@Param("limit") int limit,@Param("page") int page);

    //---------------------------------------test版----------------------------------------------------------

    //查询该sort值的上一条记录
    @Select("select * from carousel where sort < #{sort} order by sort desc limit 0,1")
    Carousel upCarousel(@Param("sort") int sort);

    //查询该sort值的下一条记录
    @Select("select * from carousel where sort > #{sort} order by sort limit 0,1")
    Carousel downCarousel(@Param("sort") int sort);

    //修改当前排序
    @Update("update carousel set sort = #{sort} where id = #{id}")
    void updateSelfSort(@Param("id")Integer id,@Param("sort") int sort);

    //修改当前的上一个或者下一个排序
    @Update("update carousel set sort = #{newSort} where sort = #{sort}")
    void updateOtherSort(@Param("newSort") int newSort,@Param("sort") int sort);

    //查询sort的最大值
    @Select("select max(sort) from carousel")
    int max();

    @Select("select * from carousel where sort = (select max(sort) from carousel)")
    Carousel maxEntity();

    //--------------------------------------mybatis报错----------------------------------------------------------------
    //批量删除
    int deleteList(@Param("ids") Integer[] ids);

}
