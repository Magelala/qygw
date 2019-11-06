package problog.mapper.carousel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import problog.entity.carousel.CompanyProfile;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/10 0:05
 */
public interface CompanyProfileMapper extends BaseMapper<CompanyProfile> {

    @Select("select * from company_profile order by sort")
    List<CompanyProfile> all();

    @Select("select count(*) from company_profile")
    Integer count();

    @Select("select * from company_profile order by sort asc limit #{limit},#{page}")
    List<CompanyProfile> page(@Param("limit") int limit,@Param("page") int page);

    @Select("select * from company_profile where title like concat('%',#{title},'%')")
    List<CompanyProfile> title(@Param("title")String title);

    @Select("select * from company_profile where title like concat('%',#{title},'%') order by sort asc limit #{limit},#{page}")
    List<CompanyProfile> titlePage(@Param("title") String title,@Param("limit") int limit,@Param("page") int page);

    @Select("select count(*) from company_profile where title like concat('%',#{title},'%')")
    Integer allTitleCount(@Param("title")String title);

    @Select("select max(sort) from company_profile")
    Integer max();

    @Select("select * from company_profile where sort < #{sort} order by sort desc limit 0,1")
    CompanyProfile up(@Param("sort") int sort);

    @Select("select * from company_profile where sort > #{sort} order by sort desc limit 0,1")
    CompanyProfile down(@Param("sort") int sort);

    @Update("update company_profile set sort = #{sort} where id = #{id}")
    void updateSelfSort(@Param("id")Integer id,@Param("sort") int sort);

    //批量删除
    int deleteList(@Param("ids") Integer[] ids);
}
