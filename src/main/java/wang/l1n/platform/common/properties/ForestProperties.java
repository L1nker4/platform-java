package wang.l1n.platform.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/22 15:18
 * @description： 
 */

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "forest")
public class ForestProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;

    private SwaggerProperties swagger = new SwaggerProperties();

    private AliyunOssProperties oss = new AliyunOssProperties();
}
