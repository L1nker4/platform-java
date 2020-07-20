package wang.l1n.platform.common.properties;

import lombok.Data;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 11:11
 * @description：
 */
@Data
public class AliyunOssProperties {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String fileHost;
}
