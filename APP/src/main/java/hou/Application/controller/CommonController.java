package hou.Application.controller;

import hou.Application.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件的上传和下载
 */

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${APP.path}") //把路径放在配置文件里
    private String path;
    /**
     * file upload
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        //file是一个临时文件，需要转存到制定位置，否则本次请求完成时临时文件就会删除
        log.info(file.toString());

        //原始文件名
        String oFile = file.getOriginalFilename();
        String prefix = oFile.substring(oFile.lastIndexOf("."));

        //使用uuid重新生成文件名，防止文件名重复被覆盖
        String fileName = UUID.randomUUID().toString()+prefix;

        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();//如果目录不存在，需要重新创建一个
        }

        file.transferTo(new File(path+fileName));
        return R.success(fileName);
    }

    /**
     * 下载文件
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try{
            //输入流，读取文件内容
            FileInputStream input = new FileInputStream(new File(path+name));
            //输出流，将文件写回浏览器，在浏览器中展示图片了
            ServletOutputStream output = response.getOutputStream();
            response.setContentType("image/jpeg");

            int length = 0;
            byte[] bytes = new byte[1024];
            while((length = input.read(bytes))!= -1){
                output.write(bytes,0,length);
                output.flush();
            }
            //关闭资源
            input.close();
            output.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
