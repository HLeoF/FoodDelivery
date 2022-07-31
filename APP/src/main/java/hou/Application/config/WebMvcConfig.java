package hou.Application.config;

import hou.Application.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 设置静态资源映射
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        log.info("开始静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展MVC框架的消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("消息转换器--->已响应");
        //创建一个新的消息转换器，作用: 将Controller返回结果，转会Jason, 输入流到Web页面
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器,低层使用JackSon将Java对象转为Jason
        converter.setObjectMapper(new JacksonObjectMapper());
        //将上述的消息转换器对象追加到MVC框架的转换容器中
        converters.add(0,converter); //index = 0, 是优先是自己创建的转换器

        super.extendMessageConverters(converters);
    }
}
