package wang.l1n.platform.common.properties;

import lombok.Data;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/19 11:30
 * @description： 
 */
@Data
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
}
