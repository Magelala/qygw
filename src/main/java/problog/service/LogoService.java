package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.logo.Logo;

/**
 * @Author: shengjun
 * @Date: 2019/10/26 22:25
 */
@Service
@Transactional
public interface LogoService {

    int update(Logo logo);

    int delete(int id);

    Logo getById(int id);
}
