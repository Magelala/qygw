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

    int addSubNav(Nav nav);

    int deleteById(int id);

    int updateById(Nav nav);

    List<Nav> allParentNav();

    List<Nav> allSubNav(int pid);

    Nav getById(int id);
}
