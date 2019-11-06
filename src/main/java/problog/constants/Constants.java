package problog.constants;

/**
 * @Author: shengjun
 * @Date: 2019/11/3 11:58
 * 一些常量,方便后面维护修改
 */
public class Constants {

    //需要放行的静态资源路径
    public static final String STATIC_RESOURCE_PATH="/bootstrap/**,/css/**,/font/**,/fonts/**,images/**,/img/**,/js/**,/lay/**,/myJs/**,/node_modules/**,/upload/**,/layui.js";

    //需要映射的文件路径,在更新图片中需求中使用,这个需要根据自己的情况进行修改
    public static final String STATIC_RESOURCE_MAPPING_PATH="file:D:/demo/qygw/src/main/resources/static/upload/picture/";

    //图片上传根路径
    public static final String UPLOAD_PICTURE_ROOT_PATH="";

    //
    public static final String UPLOAD_PICTURE_SESSION_ATTR="path";


}
