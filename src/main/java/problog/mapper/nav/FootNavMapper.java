package problog.mapper.nav;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import problog.entity.nav.FootNav;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/11/5 21:14
 */
public interface FootNavMapper extends BaseMapper<FootNav> {

    @Select("select * from foot_nav order by id")
    List<FootNav> all();

    @Select("select count(*) from foot_nav")
    Integer count();
}
