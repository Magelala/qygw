package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.foot.CopyRight;
import problog.entity.foot.Link;
import problog.mapper.foot.CopyRightMapper;
import problog.mapper.foot.LinkMapper;
import problog.service.FootService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:36
 */
@Service
@Transactional
public class FootServiceImpl implements FootService {

    @Resource
    private LinkMapper linkMapper;

    @Resource
    private CopyRightMapper copyRightMapper;


    @Override
    public int addLink(Link link) {
        return linkMapper.insert(link);
    }

    @Override
    public int delLink(int id) {
        return linkMapper.deleteById(id);
    }

    @Override
    public Link getLinkById(int id) {
        return linkMapper.selectById(id);
    }

    @Override
    public int updateLink(Link link) {
        return linkMapper.updateById(link);
    }

    @Override
    public int updateCopyRight(CopyRight copyRight) {
        return copyRightMapper.updateById(copyRight);
    }

    @Override
    public CopyRight getCopyRightById(int id) {
        return copyRightMapper.selectById(id);
    }

    @Override
    public List<Link> allLink() {
        return linkMapper.all();
    }

}
