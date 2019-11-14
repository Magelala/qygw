package problog.mapper.authorization;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import problog.entity.authorization.MenuRole;

import java.util.List;

public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    // 根据角色 id 查询
    List<MenuRole> ListByROleId(Integer rid);

    // 根据角色 id 删除
    void  deleteByRId(Integer rid);

    // 添加
    void addByRidMid(@Param("rid") Integer rid, @Param("mid") Integer mid);



    // 修改


    // 删除





}
