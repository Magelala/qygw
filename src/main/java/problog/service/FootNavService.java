package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.nav.FootNav;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/5 21:17
 */
@Service
@Transactional
public interface FootNavService {

    int add(FootNav footNav);

    int del(Integer id);

    int update(FootNav footNav);

    List<FootNav> listAll();

    FootNav getFootNavById(int id);

    Integer count();
}
