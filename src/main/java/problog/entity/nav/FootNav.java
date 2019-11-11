package problog.entity.nav;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: shengjun
 * @Date: 2019/11/5 20:56
 */
@Data
public class FootNav {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private String remark;

}
