package wang.l1n.platform.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wang.l1n.platform.common.properties.ForestProperties;
import wang.l1n.platform.common.properties.SwaggerProperties;

import java.util.Collections;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/1/22 15:17
 * @description：
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private ForestProperties properties;

    @Bean
    public Docket swaggerApi() {
        SwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger));
    }

    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }
}
