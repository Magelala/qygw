package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.nav.Nav;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 20:44
 */
@Service
@Transactional
public interface NavService {

    int addParentNav(Nav nav);

    int addSubNav(Nav nav,int id);

    int deleteById(int id);

    int deletes(Integer[] ids);

    int updateById(Nav nav);

    List<Nav> allParentNav();

    List<Nav> allSubNav(Integer pid);

    Nav getById(int id);

    int max();

    Nav up(int sort);

    Nav down(int sort);

    void updateSelf(int id,int sort);
}
