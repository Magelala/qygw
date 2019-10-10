package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.carousel.CompanyProfile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/10 0:07
 */
@Service
@Transactional
public interface CompanyProfileService {

    int add(CompanyProfile companyProfile);

    int delete(Integer id);

    int update(CompanyProfile companyProfile);

    CompanyProfile getById(Integer id);

    List<CompanyProfile> all();


}
