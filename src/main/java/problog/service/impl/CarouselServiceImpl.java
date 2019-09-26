package problog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.carousel.Carousel;
import problog.mapper.carousel.CarouselMapper;
import problog.service.CarouselService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : shengjun
 * @Date : create in
 */
@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;


    /**
     * 根据id来查找
     * @param id
     * @return 广告对象
     */
    @Override
    public Carousel selectCarouselById(Integer id){
        Carousel carousel = carouselMapper.selectById(id);
        return carousel;
    }

    public Carousel selectCarouselByTitle(String title){
        return carouselMapper.selectCarouselByTitle(title);
    }

    /**
     * 插入一个实体对象
     * @param carousel
     * @return 插入的位置
     */
    @Override
    public int insertCarousel(Carousel carousel){
//        //获取当前最大的sort值
//        Integer sort = carouselMapper.selectMaxSort();
//        //如果为0，说明没有数据;否则在最大值的基础上加一
//        if (sort == 0){
//            carousel.setSort(1);
//        }else{
//            carousel.setSort(sort+1);
//        }
        int i = carouselMapper.insert(carousel);
        System.out.println("插入的位置   ======>   "+i);
        return i;
    }

    /**
     * 根据id删除广告
     * @param id
     * @return 删除的位置
     */
    @Override
    public int deleteCarouselById(Integer id){
        int i = carouselMapper.deleteById(id);
        return i;
    }

    /**
     * 根据id来修改广告
     * @param carousel
     * @return
     */
    @Override
    public int updateCarouselById(Carousel carousel){
        int i = carouselMapper.updateById(carousel);
        return i;
    }

    /**
     * 查询所有列表
     * @return
     */
    @Override
    public List<Carousel> selectAllList(){
        List<Carousel> list = carouselMapper.selectList(null);
        return list;
    }

    @Override
    public void moveUp(Integer id) {
        Carousel carousel = carouselMapper.selectById(id);
        Carousel carouselNext = carouselMapper.moveUp(carousel.getId());

        if (null == carousel){
            return;
        }

        Integer temp = carousel.getSort();
        carousel.setSort(carouselNext.getSort());
        carousel.setSort(temp);

        //更新到数据库
        carouselMapper.updateById(carousel);
        carouselMapper.updateById(carouselNext);
    }

    @Override
    public void moveDown(Integer id) {
        Carousel carousel = carouselMapper.selectById(id);
        Carousel carouselPrev = carouselMapper.moveDown(carousel.getId());

        if (carousel==null){
            return;
        }

        Integer temp = carousel.getSort();
        carousel.setSort(carouselPrev.getSort());
        carouselPrev.setSort(temp);

        carouselMapper.updateById(carousel);
        carouselMapper.updateById(carouselPrev);
    }

}
