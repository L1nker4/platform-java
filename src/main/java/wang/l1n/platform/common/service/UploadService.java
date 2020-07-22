package wang.l1n.platform.common.service;

import org.springframework.web.multipart.MultipartFile;
import wang.l1n.platform.common.entity.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 14:17
 * @description： 文件上传服务 接口类
 */
public interface UploadService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    CommonResult upload(MultipartFile file);

    /**
     * 测试文件上传
     * @param file 文件
     * @return 地址
     */
    CommonResult swaggerFileUpload(MultipartFile file);
}
