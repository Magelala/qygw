package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.foot.CopyRight;
import problog.entity.foot.Link;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:36
 */
@Service
@Transactional
public interface FootService {

    int addLink(Link link);

    int delLink(int id);

    Link getLinkById(int id);

    int updateLink(Link link);

    int updateCopyRight(CopyRight copyRight);

    CopyRight getCopyRightById(int id);

    List<Link> allLink();


}
