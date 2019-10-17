package problog.entity.Article;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ArticleComment {
        private Integer id;
        private Integer articleId;
        private Integer commentId;
        private Timestamp createByDate;
}
