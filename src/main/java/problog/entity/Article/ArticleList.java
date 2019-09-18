package problog.entity.Article;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleList {
    private Integer id;
    private String title;
    private String author;
    private String category;
    private Timestamp time;
}
