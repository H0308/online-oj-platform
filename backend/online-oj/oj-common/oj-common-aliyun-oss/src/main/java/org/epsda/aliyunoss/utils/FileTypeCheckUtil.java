package org.epsda.aliyunoss.utils;

import org.epsda.aliyunoss.constants.OssConstants;
import org.epsda.aliyunoss.enums.FileType;
import org.epsda.aliyunoss.exception.OssException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static org.epsda.aliyunoss.enums.FileType.IMAGE;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件类型检查工具类
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 14:49
 * Package Name: org.epsda.aliyunoss.utils
 * Project Name: online-oj
 */
public class FileTypeCheckUtil {

    // 校验文件类型
    public static void validateFileType(MultipartFile file, FileType fileType) {
        String filename = file.getOriginalFilename();
        if (!StringUtils.hasText(filename)) {
            throw new OssException("文件不能为空");
        }
        int pointIndex = filename.lastIndexOf(".");
        if (pointIndex < 0) {
            throw new OssException("无法确定文件类型");
        }
        String fileExtension = filename.substring(pointIndex).toLowerCase(); // 校验文件后缀，确保小写
        String contentType = file.getContentType(); // 校验文件MIME类型
        if (!StringUtils.hasText(contentType)) {
            throw new OssException("文件类型不能为空");
        }
        boolean isValid = switch (fileType) {
            case IMAGE -> OssConstants.SUPPORTED_IMAGE_EXTENSIONS.contains(fileExtension) &&
                    OssConstants.SUPPORTED_IMAGE_MIME.contains(contentType);
        };

        if (!isValid) {
            throw new OssException("文件类型或者内容错误");
        }
    }
}
