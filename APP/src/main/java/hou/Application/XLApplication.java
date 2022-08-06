package hou.Application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j //get set method 省略, 可以直接调用log
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class XLApplication {
    public static void main(String[] args) {
        SpringApplication.run(XLApplication.class,args);
        log.info("项目已经启动");
    }
}
