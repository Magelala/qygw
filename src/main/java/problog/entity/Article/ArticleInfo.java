package problog.entity.Article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String summary;
    private Integer traffic;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;
}
