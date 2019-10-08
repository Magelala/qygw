package problog.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.carousel.Carousel;



import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public Carousel getById(Integer id) {
        return carouselMapper.selectById(id);
    }

    @Override
    public int save(Carousel carousel) {
        return carouselMapper.insert(carousel);
    }

    @Override
    public int delete(Integer id) {
        return carouselMapper.deleteById(id);
    }

    @Override
    public int batchDelete(Integer[] ids) {
        return carouselMapper.deleteList(ids);
    }

    @Override
    public int update(Carousel carousel) {
        return carouselMapper.updateById(carousel);
    }

    @Override
    public List<Carousel> all() {
        return carouselMapper.all();
    }

    @Override
    public List<Carousel> getCarouselByTitle(String title,int limit,int page) {
        return carouselMapper.selectTitlePage(title,limit,page);
    }

    /**
     * sort的上一条字段
     * @param sort 分类
     * @return 轮播广告对象
     */
    @Override
    public Carousel up(int sort) {
        return carouselMapper.upCarousel(sort);
    }

    /**
     * sort的下一条字段
     * @param sort
     * @return
     */
    @Override
    public Carousel down(int sort) {
        return carouselMapper.downCarousel(sort);
    }

    @Override
    public void updateSelfSort(Integer id, int sort) {
        carouselMapper.updateSelfSort(id,sort);
    }

    @Override
    public void updateOtherSort(int newSort, int sort) {
        carouselMapper.updateOtherSort(newSort, sort);
    }

    @Override
    public int max() {
        return carouselMapper.max();
    }

    @Override
    public Carousel maxSort() {
        return carouselMapper.maxEntity();
    }

}