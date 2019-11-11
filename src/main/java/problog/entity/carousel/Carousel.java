package problog.entity.carousel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Carousel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createDate;

    //轮播图路径
    private String imgUrl;

    //轮播图标题
    private String title;

    //轮播图链接
    private String imgLink;

    //备注
    private String remark;

    //排序
    private Integer sort;
}