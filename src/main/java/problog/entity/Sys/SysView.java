package problog.entity.Sys;

import java.sql.Timestamp;

public class SysView {
    private Integer id;
    private String ip;
    private Timestamp createByDate;
    private Integer number;

    @Override
    public String toString() {
        return "SysView{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", createByDate=" + createByDate +
                ", number=" + number +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getCreateByDate() {
        return createByDate;
    }

    public void setCreateByDate(Timestamp createByDate) {
        this.createByDate = createByDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
