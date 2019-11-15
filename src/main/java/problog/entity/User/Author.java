package problog.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import problog.entity.authorization.Role;

@Data
public class Author {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String tel;
    private String email;
    private String role;
    private String articleNum;
    private String  picture;
    private String context;
    private Role rrole;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Role getRrole() {
        return rrole;
    }

    public void setRrole(Role rrole) {
        this.rrole = rrole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(String articleNum) {
        this.articleNum = articleNum;
    }
}
