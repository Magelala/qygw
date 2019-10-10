package problog.entity.carousel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: shengjun
 * @Date: 2019/10/10 0:00
 * 企业简介轮播图管理
 */
@Data
public class CompanyProfile {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createTime;

    private String imgUrl;

    private Integer sort;

    private String message;

}
