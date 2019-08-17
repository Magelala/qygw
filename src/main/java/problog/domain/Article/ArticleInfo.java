package problog.domain.Article;

import java.sql.Timestamp;

public class ArticleInfo {
    private Integer id;
    private String title;
    private String summary;
    private Integer traffic;
    private Timestamp createByDate;
    private Timestamp modifiedByDate;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", traffic=" + traffic +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
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
