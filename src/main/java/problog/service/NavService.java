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

    int deleteById(int id);

    int deletes(Integer[] ids);

    int updateById(Nav nav);

    List<Nav> allParentNav();

    List<Nav> allSubNav(Integer pid);

    Nav getById(int id);

    Integer max();

    Integer maxSub(int pid);

    Nav up(int sort);

    Nav upSub(int sort,int pid);

    Nav down(int sort);

    Nav downSub(int sort,int pid);

    void updateSelf(int id,int sort);

    void updateSubSelf(int id,int sort,int pid);

    String selectTitle(int pid);
}
