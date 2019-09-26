package problog.entity.response;

import lombok.Data;

@Data
public class UploadBean {

    private int code;
    private String msg;
    private String src;
    public UploadBean(){}

}