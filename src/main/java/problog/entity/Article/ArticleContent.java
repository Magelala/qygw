package problog.entity.Article;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.soap.Text;
import java.sql.Timestamp;

@Data
public class ArticleContent {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer authorId;
    private Integer categoryId;
    private String title;
    private String context;
    private String summary;
    private String cover;
    private String keywords;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;
    private String author;

}
