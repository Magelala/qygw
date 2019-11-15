package problog.entity.foot;

import lombok.Data;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 13:27
 */
@Data
public class CopyRight {

    private Integer id;

    private String name;

    private String icp;

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

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }
}
