package problog.mapper.carousel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import problog.entity.carousel.Carousel;

/**
 * @Author : shengjun
 * @Date : create in

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
}
 */