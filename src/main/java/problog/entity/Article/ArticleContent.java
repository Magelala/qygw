package problog.entity.Article;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Timestamp;

@Data
public class ArticleContent {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String context;
    private Integer articleId;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;
    private Integer isTop;

  //  file用来存放上传的文件
    private MultipartFile file;

}
