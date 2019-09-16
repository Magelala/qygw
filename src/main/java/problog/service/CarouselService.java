package problog.service;

import org.springframework.stereotype.Service;
import problog.entity.carousel.Carousel;

import java.util.List;

/**
 * @Author : shengjun
 * @Date : create in
 */
@Service
public interface CarouselService {

    Carousel selectCarouselById(Integer id);

    Carousel selectCarouselByTitle(String title);

    void insertCarousel(Carousel carousel);

    int deleteCarouselById(Integer id);

    int updateCarouselById(Carousel carousel);

    List<Carousel> selectAllList();

    //上移
    void moveUp(Integer id);

    //下移
    void moveDown(Integer id);

}
