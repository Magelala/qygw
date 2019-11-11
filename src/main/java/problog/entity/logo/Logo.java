package problog.entity.logo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: shengjun
 * @Date: 2019/10/26 22:23
 */

@Data
public class Logo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer authorId;
    private String authorName;
    private String src;
    private String url;

}
