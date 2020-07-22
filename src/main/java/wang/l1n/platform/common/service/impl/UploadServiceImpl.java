package wang.l1n.platform.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.service.AliyunOssService;
import wang.l1n.platform.common.service.UploadService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 14:18
 * @description： 文件上传服务 实现类
 */

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliyunOssService aliyunOssService;

    private static final String KEY_PREFIX = "test";


    @Override
    public CommonResult upload(MultipartFile file) {
        String url = aliyunOssService.swaggerFileUpload(KEY_PREFIX, file);
        return new CommonResult().data(url);
    }

    @Override
    public CommonResult swaggerFileUpload(MultipartFile file) {
        String url = aliyunOssService.swaggerFileUpload(KEY_PREFIX, file);
        return new CommonResult().data(url);
    }
}
