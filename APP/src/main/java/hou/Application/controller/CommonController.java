package hou.Application.controller;

import hou.Application.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        String uuid = UUID.randomUUID().toString()+prefix;

        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();//如果目录不存在，需要重新创建一个
        }

        file.transferTo(new File(path+uuid));
        return null;
    }
}
