package problog.entity.Article;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleContent {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String author; //作者
    private Integer classify; //分类目录
    private String title;  //文章标题
    private String keywords; //关键字
    private String summary; //摘要
    private String context; //文章内容

    private String isTop; //是否置顶,0不置顶,1置顶

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createByDate; //创建时间

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp modifiedByDate; //修改时间

    private String status; //文章的状态,1已发表

    private String picture; //作者

    private int views; //文章的访问量

    private int sort; //排序,对文章的置顶有作用

    private String name;  //存放category表中的name字段数据

}
