package problog.mapper.Category;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.Category.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    //
    @Select("select * from category order by sort asc limit #{limit},#{page}")
    List<Category> showPage(@Param("limit") int limit, @Param("page") int page);

}
