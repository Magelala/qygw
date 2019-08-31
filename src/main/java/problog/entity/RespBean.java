package problog.entity;

import lombok.Data;
/**
用来存放操作是否成功的提示信息

*/
@Data
public class RespBean {
    private String status;
    private String msg;

    public RespBean(String status, String msg) {
            this.status=status;
            this.msg=msg;

    }
}
