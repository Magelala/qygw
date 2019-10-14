package problog.mapper.nav;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import problog.entity.nav.Nav;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 20:43
 */
public interface NavMapper extends BaseMapper<Nav> {

    //查询父导航,升序
    @Select("select * from nav where pid = 0 order by sort asc")
    List<Nav> allParentNav();

    //查询子导航,升序
    @Select("select * from nav where pid = #{pid} order by sort asc")
    List<Nav> allSubNav(@Param("pid") int pid);

    @Insert("insert into nav values (?,?,?,?) where pid = 0")
    int addParentNav(@Param("nav") Nav nav);

    @Insert("insert into nav values (?,?,?,?,?)")
    int addSubNav(@Param("nav") Nav nav);

    //删除唯一标识符即可,不需要分类进行删除
    @Delete("delete * from nav where id = #{id}")
    int deleteNav(@Param("id") int id);

    //更新和删除同理,就直接使用BaseMapper接口中的方法了
}
