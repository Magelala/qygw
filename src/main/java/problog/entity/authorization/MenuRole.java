package problog.entity.authorization;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:MenuRole
 * @Author:Timelin
 **/
@Data
public class MenuRole implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer mid;
    private Integer rid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}