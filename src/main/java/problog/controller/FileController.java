package problog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author : shengjun
 * @Date : create in
 */
@RestController
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file",required = false) MultipartFile file){
        try{
//            用于获取项目根目录
            File f = new File("");
            String filePath = f.getCanonicalPath()+"\\src\\main\\resources\\static\\upload\\picture\\";
            System.out.println(filePath);
            if (file.isEmpty()){
                return "文件夹为空";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为："+fileName);
            //设置文件存储路径
            String path = filePath + fileName;
            File dest = new File(path);
            //检测是否存在目录
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs(); //新建文件夹
            }
            file.transferTo(dest); //文件写入
//            System.out.println("这是文件的路径"+filePath);

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
