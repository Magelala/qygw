package problog.entity.nav;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 20:38
 */
@Data
public class Nav {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; //菜单名称
    private String authorName;
    private Integer pid; //上级导航的id,0表示第一级导航
    private String url; //导航链接
    private Integer authorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp updateTime;

    private  Boolean statues; //布尔类型,0隐藏,1显示,默认设置了显示
    private String description;
    private int sort; //排序导航

}
