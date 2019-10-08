package problog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     *
     * @param file 文件
     * @param path   文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){

        //使用原文件名
         String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断上传的文件后缀是否为图片类型
     * @param fileName 上传的文件名称,例如1.jpg
     * @return
     */
    public static boolean isImageSuffix(String fileName){
        int begin = fileName.indexOf(".");
        int last = fileName.length();
        //截取文件的后缀名
        String suffix = fileName.substring(begin,last);
        if (suffix.endsWith(".jpg") || suffix.endsWith(".jpeg") || suffix.endsWith(".gif") || suffix.endsWith(".svg") || suffix.endsWith(".BMP") || suffix.endsWith(".png")){
            return true;
        }
        return false;
    }

}
