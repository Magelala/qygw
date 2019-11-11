package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.logo.Logo;
import problog.mapper.logo.LogoMapper;
import problog.service.LogoService;

import javax.annotation.Resource;

/**
 * @Author: shengjun
 * @Date: 2019/10/26 22:25
 */
@Service
@Transactional
public class LogoServiceImpl implements LogoService {

    @Resource
    private LogoMapper logoMapper;

    @Override
    public int update(Logo logo) {
        return logoMapper.updateById(logo);
    }

    @Override
    public int delete(int id) {
        return logoMapper.deleteById(id);
    }

    @Override
    public Logo getById(int id) {
        return logoMapper.selectById(id);
    }
}
