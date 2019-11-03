package problog.entity.foot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:29
 */
@Data
public class Link implements Serializable {

    private static final long serialVersionUID = -4725937550197599617L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String linkName;
    private String linkPicture;
    private String linkUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createTime;
    private String remark;

}
