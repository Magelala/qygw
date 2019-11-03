package problog.mapper.foot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import problog.entity.foot.Link;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:34
 */
public interface LinkMapper extends BaseMapper<Link> {

    @Select("select * from link")
    List<Link> all();
}
