package problog.domain.Category;

import java.sql.Timestamp;

public class CategoryInfo {
    private Integer id;
    private Integer categoryId;
    private  Integer articleId;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", articleId=" + articleId +
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
