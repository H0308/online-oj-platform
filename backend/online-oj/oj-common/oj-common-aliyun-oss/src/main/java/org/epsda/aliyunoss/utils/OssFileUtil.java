package org.epsda.aliyunoss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import jakarta.annotation.PostConstruct;
import org.epsda.aliyunoss.exception.OssException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 15:02
 * Package Name: org.epsda.aliyunoss.utils
 * Project Name: online-oj
 */
@Component
@ConditionalOnProperty(prefix = "aliyun.oss", name = "endpoint")
public class OssFileUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    private OSS ossClient;

    @PostConstruct
    public void init() {
        this.ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    // 上传文件
    public String uploadFileToOss(MultipartFile file) {
        // 官方没有提供批量上传的接口，并且用户可以上传的文件数量和大小比较小
        // 所以上层可以循环调用
        // 保证文件名唯一，防止同名但是内容不同的文件
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try {
            ossClient.putObject(bucketName, fileName, file.getInputStream()); // 上传文件
        } catch (IOException e) {
            throw new OssException("文件上传错误");
        }
        return "https://" + bucketName + "." + endpoint + "/" + fileName; // 返回文件在OSS的地址
    }

    // 从链接下载源文件
    public MultipartFile downloadFileFromOss(String fileUrl) {
        // 从fileUrl截取出文件名
        int index = fileUrl.lastIndexOf("/");
        String objectName = fileUrl.substring(index + 1);
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream inputStream = ossObject.getObjectContent();

        // 读取 InputStream 处理数据
        byte[] buffer = null;
        try {
            buffer = inputStream.readAllBytes();
            inputStream.close();
        } catch (IOException e) {
            throw new OssException("文件读取错误");
        }

        return new MockMultipartFile("file", objectName,
                "application/octet-stream", buffer);
    }
}
