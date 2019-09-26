package problog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import problog.entity.response.UploadBean;
import problog.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/native")
    public UploadBean nativeUpload(@RequestParam("file") MultipartFile file){
        UploadBean uploadBean = new UploadBean();
        try{
//            用于获取项目根目录
            File f = new File("");
            String filePath = f.getCanonicalPath()+"\\src\\main\\resources\\static\\upload\\picture\\";
            System.out.println(filePath);
            //获取文件名
            String fileName = file.getOriginalFilename();
            //上传的必须是图片
            if (FileUtils.isImageSuffix(fileName)){
                //设置文件存储路径
                String path = filePath + fileName;
                File dest = new File(path);
                //检测是否存在目录
                if (!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs(); //新建文件夹
                }
                file.transferTo(dest); //文件写入
                System.out.println("这是文件的路径"+path);
                String substring = path.substring(path.length()-fileName.length()-16);
                substring = substring.replace("\\","/");
                request.getSession().setAttribute("path",substring);
                System.out.println(substring);
                uploadBean.setCode(0);
                uploadBean.setSrc(substring);
                return uploadBean;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        uploadBean.setCode(-1);
        uploadBean.setSrc("");
        return uploadBean;
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @PostMapping("/batch")
    public UploadBean handleFileUpload(HttpServletRequest request){
        UploadBean uploadBeans = new UploadBean();
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i=0;i<files.size();++i){
            file = files.get(i);
            if (!file.isEmpty()){
                try{
                    File f = new File("");
                    String filePath = f.getCanonicalPath()+"\\src\\main\\resources\\static\\upload\\";
                    System.out.println(filePath);
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    System.out.println("第"+i+"个文件上传失败 ==>" +e.getMessage());
                    uploadBeans.setCode(-1);
                    uploadBeans.setSrc("");
                    return uploadBeans;
                }
            }else{
                uploadBeans.setCode(-1);
                uploadBeans.setMsg("第"+i+"个文件上传失败，因为文件为空");
                return uploadBeans;
            }
        }
        uploadBeans.setCode(0);
        uploadBeans.setSrc("");
        return uploadBeans;
    }

}
