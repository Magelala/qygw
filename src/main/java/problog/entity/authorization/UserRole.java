package problog.entity.authorization;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:UserRole
 * @Author:Timelin
 **/
@Data
public class UserRole implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer urid;
    private Integer rid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
