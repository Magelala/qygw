package problog.mapper.carousel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.carousel.Carousel;

import java.util.List;

/**
 * @Author : shengjun
 * @Date : create in
 */
@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {

    //根据标题来查询
    Carousel selectCarouselByTitle(String title);


    //查询最大分类值
    Integer selectMaxSort();

    //上移
    Carousel moveUp(Integer id);

    //下移
    Carousel moveDown(Integer id);

    //分页查询，按照升序排序，根据两个请求参数指定，page:当前页,limit:限制一页显示多少条数据
    @Select("select * from carousel" + " order by id asc limit #{limit},#{page}")
    List<Carousel> selectPage(@Param("limit") int limit, @Param("page") int page);
}
