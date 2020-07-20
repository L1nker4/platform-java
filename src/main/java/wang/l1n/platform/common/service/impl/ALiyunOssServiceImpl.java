package wang.l1n.platform.common.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import wang.l1n.platform.common.exception.ForestException;
import wang.l1n.platform.common.properties.AliyunOssProperties;
import wang.l1n.platform.common.properties.ForestProperties;
import wang.l1n.platform.common.service.AliyunOssService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 11:23
 * @description： 阿里云 OSS 服务实现类
 */
@Component
@Slf4j
public class ALiyunOssServiceImpl implements AliyunOssService {

    @Autowired
    private ForestProperties properties;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat formatTime = new SimpleDateFormat("HH-mm-ss");

    private static final String ERROR_MESSAGE = "上传失败";

    @Override
    public String upload(InputStream inputStream, String keyPrefix, String fileName) {
        AliyunOssProperties ossProperties = properties.getOss();
        OSS ossClient = new OSSClientBuilder()
                .build(ossProperties.getEndpoint(),
                        ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret());
        try {
            //bucket不存在则进行创建
            String bucketName = ossProperties.getBucketName();
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest bucketRequest = new CreateBucketRequest(bucketName);
                ossClient.createBucket(bucketRequest);
            }
            //拼接文件路径
            String fileExt = fileName.substring(fileName.lastIndexOf(StringPool.DOT) + 1).toLowerCase();
            String fileLey = keyPrefix + StringPool.SLASH
                    + format.format(new Date()) + StringPool.UNDERSCORE
                    + formatTime.format(new Date()) + StringPool.UNDERSCORE
                    + new Random().nextInt(100000) + StringPool.DOT + fileExt;
            String fileUrl = ossProperties.getFileHost() + StringPool.SLASH + fileLey;
            Optional<PutObjectResult> resultOptional = Optional.ofNullable(ossClient.putObject(new PutObjectRequest(ossProperties.getBucketName(), fileLey, inputStream)));
            ossClient.setBucketAcl(ossProperties.getBucketName(), CannedAccessControlList.PublicRead);
            if (resultOptional.isPresent()) {
                log.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
            return null;
        } catch (OSSException e) {
            throw new ForestException(ERROR_MESSAGE + e.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public String multipartFileUpload(HttpServletRequest request, String keyPrefix) {
        StringBuilder filePath = new StringBuilder();
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                String fileName = Objects.requireNonNull(file).getOriginalFilename();
                try {
                    InputStream inputStream = file.getInputStream();
                    String imageUrl = this.upload(inputStream, keyPrefix, fileName);
                    filePath.append(imageUrl).append(StringPool.COMMA);
                    inputStream.close();
                } catch (IOException e) {
                    throw new ForestException("上传失败," + "文件格式不正确");
                }
            }
            if (filePath.toString().endsWith(StringPool.COMMA)) {
                filePath = new StringBuilder(filePath.substring(0, filePath.length() - 1));
            }
            return filePath.toString();
        } else {
            throw new ForestException("请选择上传文件");
        }
    }

    @Override
    public String swaggerFileUpload(String keyPrefix, MultipartFile file) {
        StringBuilder filePath = new StringBuilder();
        String fileName = Objects.requireNonNull(file).getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            String imageUrl = this.upload(inputStream, keyPrefix, fileName);
            filePath.append(imageUrl);
            inputStream.close();
        } catch (IOException e) {
            throw new ForestException("上传失败," + "文件格式不正确");
        }
        if (filePath.toString().endsWith(StringPool.COMMA)) {
            filePath = new StringBuilder(filePath.substring(0, filePath.length() - 1));
        }
        return filePath.toString();
    }

}
