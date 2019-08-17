package problog.domain.Article;

import java.sql.Timestamp;

public class ArticleComment {
        private Integer id;
        private Integer articleId;
        private Integer commentId;

    @Override
    public String toString() {
        return "ArticleComment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", commentId=" + commentId +
                ", createByDate=" + createByDate +
                '}';
    }

    private Timestamp createByDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Timestamp getCreateByDate() {
        return createByDate;
    }

    public void setCreateByDate(Timestamp createByDate) {
        this.createByDate = createByDate;
    }
}
