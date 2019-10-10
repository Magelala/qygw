package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.carousel.CompanyProfile;
import problog.mapper.carousel.CompanyProfileMapper;
import problog.service.CompanyProfileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/10 0:07
 */
@Service
@Transactional
public class CompanyProfileImpl implements CompanyProfileService {

    @Resource
    private CompanyProfileMapper companyProfileMapper;

    @Override
    public int add(CompanyProfile companyProfile) {
        return companyProfileMapper.insert(companyProfile);
    }

    @Override
    public int delete(Integer id) {
        return companyProfileMapper.deleteById(id);
    }

    @Override
    public int update(CompanyProfile companyProfile) {
        return companyProfileMapper.updateById(companyProfile);
    }

    @Override
    public CompanyProfile getById(Integer id) {
        return companyProfileMapper.selectById(id);
    }

    @Override
    public List<CompanyProfile> all() {
        return companyProfileMapper.all();
    }
}
