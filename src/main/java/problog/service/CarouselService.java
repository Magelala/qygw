package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.carousel.Carousel;

import java.util.List;

@Service
@Transactional
public interface CarouselService {

    Carousel getById(Integer id);

    int save(Carousel carousel);

    int delete(Integer id);

    int batchDelete(Integer[] ids);

    int update(Carousel carousel);

    List<Carousel> all();

    List<Carousel> getCarouselByTitle(String title,int limit,int page);

    Carousel up(int sort);

    Carousel down(int sort);

    void updateSelfSort(Integer id,int sort);

    void updateOtherSort(int newSort,int sort);

    int max();

    Carousel maxSort();

}
