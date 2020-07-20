package wang.l1n.platform.common.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 11:22
 * @description： 阿里云 OSS 服务类接口
 */

public interface AliyunOssService {

    /**
     * 文件上传
     * @param inputStream       输入流
     * @param keyPrefix         key前缀
     * @param fileName          文件名
     * @return                  文件URL
     */
    String upload(InputStream inputStream, String keyPrefix, String fileName);

    /**
     * request请求多文本上传
     * @param request           request对象
     * @param keyPrefix         key前缀
     * @return                  文件URL
     */
    String multipartFileUpload(HttpServletRequest request, String keyPrefix);

    /**
     * Swagger 测试上传
     * @param keyPrefix         key前缀
     * @param file              文件
     * @return                  文件URL
     */
    String swaggerFileUpload(String keyPrefix, MultipartFile file);
}
