package wang.l1n.platform.platform.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.service.UploadService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 14:30
 * @description： 文件上传接口
 */
@RestController
@RequestMapping("/upload")
@Api(value = "/upload", tags = {"上传"})
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping()
    @ApiOperation("上传")
    public CommonResult upload(@RequestBody MultipartFile file){
        return uploadService.upload(file);
    }

    @PostMapping("/test")
    @ApiOperation("Swagger测试文件上传")
    public CommonResult swaggerFileUpload(@RequestBody MultipartFile file){
        return uploadService.swaggerFileUpload(file);
    }
}
