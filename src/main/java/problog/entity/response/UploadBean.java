package problog.entity.response;

import lombok.Data;

@Data
public class UploadBean {

    private int code;
    private String msg;
    private String src;
    public UploadBean(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}