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

    //时间索引的最大值与最小值
    private String maxTimeStr;
    private String minTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxTimeStr() {
        return maxTimeStr;
    }

    public void setMaxTimeStr(String maxTimeStr) {
        this.maxTimeStr = maxTimeStr;
    }

    public String getMinTimeStr() {
        return minTimeStr;
    }

    public void setMinTimeStr(String minTimeStr) {
        this.minTimeStr = minTimeStr;
    }
}
