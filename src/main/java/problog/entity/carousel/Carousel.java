package problog.entity.carousel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;
/**
 * @Entity注解标识这个类和数据库做映射，当从数据库中取数据时，我们只需要读取实体类，后台会自动将
 * 数据库中的数据填充到对象中
 * @Author : shengjun
 * @Date : create in
 */
@Data
public class Carousel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //创建时间
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

    public Carousel(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }
}
