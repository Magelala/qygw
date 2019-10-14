package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.nav.Nav;
import problog.mapper.nav.NavMapper;
import problog.service.NavService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 20:46
 */
@Service
@Transactional
public class NavServiceImpl implements NavService {

    @Resource
    private NavMapper navMapper;

    @Override
    public int addParentNav(Nav nav) {
        return navMapper.addParentNav(nav);
    }

    @Override
    public int addSubNav(Nav nav) {
        return navMapper.addSubNav(nav);
    }

    @Override
    public int deleteById(int id) {
        return navMapper.deleteById(id);
    }

    @Override
    public int updateById(Nav nav) {
        return navMapper.updateById(nav);
    }

    @Override
    public List<Nav> allParentNav() {
        return navMapper.allParentNav();
    }

    @Override
    public List<Nav> allSubNav(int pid) {
        return navMapper.allSubNav(pid);
    }

    @Override
    public Nav getById(int id) {
        return navMapper.selectById(id);
    }
}
