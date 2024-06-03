package ImageService.controller;

import model.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam(value = "file",required = false) MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return ResponseResult.fail();
        }
        // 获取传过来的文件名字
        String OriginalFilename = file.getOriginalFilename();
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName = System.currentTimeMillis() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        String path = "D:\\hcf-study\\课设1\\微服务改造\\SunNeverSets\\image-service\\src\\main\\resources\\static\\public\\imgs\\images\\";
                File dest = new File(path + fileName);
        // 判断文件是否存在
        if (!dest.getParentFile().exists()) {
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            return new ResponseResult(200, "文件上传成功", fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail();
        }
    }
}
