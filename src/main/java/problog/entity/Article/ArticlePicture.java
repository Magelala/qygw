package problog.entity.Article;

import java.sql.Timestamp;

public class ArticlePicture {
    private Integer id;
    private Integer articleId;
    private Integer authorId;
    private String pictureBy;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;

    @Override
    public String toString() {
        return "ArticlePicture{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", authorId=" + authorId +
                ", pictureBy='" + pictureBy + '\'' +
                ", createByDate=" + createByDate +
                ", modifiedByDate=" + modifiedByDate +
                '}';
    }

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getPictureBy() {
        return pictureBy;
    }

    public void setPictureBy(String pictureBy) {
        this.pictureBy = pictureBy;
    }

    public Timestamp getCreateByDate() {
        return createByDate;
    }

    public void setCreateByDate(Timestamp createByDate) {
        this.createByDate = createByDate;
    }

    public Timestamp getModifiedByDate() {
        return modifiedByDate;
    }

    public void setModifiedByDate(Timestamp modifiedByDate) {
        this.modifiedByDate = modifiedByDate;
    }
}
