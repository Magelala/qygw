package problog.entity.nav;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: shengjun
 * @Date: 2019/10/14 20:38
 */
@Data
public class Nav {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; //菜单名称
    private Integer pid; //上级导航的id,0表示第一级导航
    private String url; //导航链接
    private Integer authorId;
    private String authorName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Timestamp updateTime;

    private  Boolean statues; //布尔类型,0隐藏,1显示,默认设置了显示
    private String description;
    private int sort; //排序导航

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatues() {
        return statues;
    }

    public void setStatues(Boolean statues) {
        this.statues = statues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
