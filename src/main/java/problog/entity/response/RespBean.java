package problog.entity.response;

import lombok.Data;

/**
 * 用来存放操作是否成功的提示信息
 * 有ok方法将 RespBean对象返回到浏览器
 * 也有fail方法 返回错误的信息
 * @param <T>
 */
@Data
public class RespBean<T> {

    //响应数据
    private T respData;

    //请求是否成功
    private boolean success;

    //状态码
    private int code = -1;

    //错误信息
    private String msg;

    public RespBean(){}

    /**
     * 成功时调用
     * @param success
     */
    public RespBean(boolean success){
        this.success = success;
    }

    public RespBean(boolean success,T respData){
        this.success = success;
        this.respData = respData;
    }

    public RespBean(boolean success,T respData,int code){
        this.success = success;
        this.respData = respData;
        this.code = code;
    }

    /**
     *用于错误时调用
     * @param success
     * @param msg
     */
    public RespBean(boolean success,String msg){
        this.success = success;
        this.msg = msg;
    }
    public RespBean(boolean success,String msg,int code){
        this.success = success;
        this.msg = msg;
        this.code= code;
    }

    public static RespBean ok(){
        return new RespBean(true);
    }

    public static <T> RespBean ok(T respData){
        return new RespBean<T>(true,respData);
    }

    public static <T> RespBean ok(int code){
        return new RespBean(true,null,code);
    }

    public static <T> RespBean ok(T respData,int code){
        return new RespBean<T>(true,respData,code);
    }

    public static RespBean fail(){
        return new RespBean(false);
    }

    public static RespBean fail(String msg){
        return new RespBean(false,msg);
    }

    public static RespBean fail(int code){
        return new RespBean(false,null,code);
    }

    public static RespBean fail(int code,String msg){

        return new RespBean(false,msg,code);
    }

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
}
