package problog.entity.response;

import lombok.Data;

@Data
public class ResResult<T> {

    private String id;
    private int code;
    private String msg;
    private T data;
    //页面总数
    private int count;
    //每个页面显示多少条数据
    private int limit;
    //当前页
    private int page;

    public ResResult(){}

    public ResResult(int code,String msg ,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResResult ok(T data){
        return new ResResult<T>(0,"成功",data);
    }

    public static <T> ResResult fail(int code){
        return new ResResult<T>(code,"失败",null);
    }

}
