package problog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.nav.FootNav;
import problog.mapper.nav.FootNavMapper;
import problog.service.FootNavService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/5 21:22
 */
@Service
@Transactional
public class FootNavServiceImpl implements FootNavService {


    @Resource
    private FootNavMapper footNavMapper;

    @Override
    public int add(FootNav footNav) {
        return footNavMapper.insert(footNav);
    }

    @Override
    public int del(Integer id) {
        return footNavMapper.deleteById(id);
    }

    @Override
    public int update(FootNav footNav) {
        return footNavMapper.updateById(footNav);
    }

    @Override
    public List<FootNav> listAll() {
        return footNavMapper.all();
    }

    @Override
    public FootNav getFootNavById(int id) {
        return footNavMapper.selectById(id);
    }

    @Override
    public Integer count() {
        return footNavMapper.count();
    }
}
