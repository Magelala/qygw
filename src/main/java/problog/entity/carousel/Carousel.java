package problog.entity.carousel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Entity注解标识这个类和数据库做映射，当从数据库中取数据时，我们只需要读取实体类，后台会自动将
 * 数据库中的数据填充到对象中
 * @Author : shengjun
 * @Date : create in
 */
@Data
public class Carousel {

    private Integer id;

    //创建时间
    private Date createDate;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
