package problog.mapper.nav;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
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

    //添加子导航,id为父导航,需要先获取到
    @Insert("insert into nav values (?,?,?,?,?)")
    int addSubNav(@Param("nav") Nav nav,@Param("id") int id);

    //删除唯一标识符即可,不需要分类进行删除
    @Delete("delete * from nav where id = #{id}")
    int deleteNav(@Param("id") int id);

    //更新和删除同理,就直接使用BaseMapper接口中的方法了
    int deletes(@Param("ids") Integer[] ids);

    @Select("select * from nav where id = #{id}")
    Nav getById(@Param("id") Integer id);

    //查询父导航中最大的sort值
    @Select("select max(sort) from nav where pid=0")
    int max();

    //查询子导航中最大的sort值,先要获取父导航id
    @Select("select max(sort) from nav where pid = #{id}")
    int maxSub(Integer id);

    @Select("select * from nav where pid = 0 AND sort < #{sort} order by sort limit 0,1")
    Nav upNav(@Param("sort") int sort);

    @Select("select * from nav where pid = 0 AND sort > #{sort} order by sort limit 0,1")
    Nav downNav(@Param("sort") int sort);

    //修改当前排序
    @Update("update nav set sort = #{sort} where id = #{id} AND pid = 0")
    void updateSelfSort(@Param("id")Integer id,@Param("sort") int sort);
}
