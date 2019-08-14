package problog.domain.Article;

import java.sql.Timestamp;

public class ArticleContent {
    private Integer id;
    private String context;
    private Integer articleId;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;
    private Integer isTop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }
}
