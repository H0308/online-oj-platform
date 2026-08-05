package org.epsda.user.manager.impl;

import jakarta.annotation.Resource;
import org.epsda.aliyunoss.enums.FileType;
import org.epsda.aliyunoss.utils.FileTypeCheckUtil;
import org.epsda.aliyunoss.utils.OssFileUtil;
import org.epsda.base.exception.UserException;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.manager.FileManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 15:08
 * Package Name: org.epsda.user.manager
 * Project Name: online-oj
 */
@Service
public class FileManagerImpl implements FileManager {
    @Resource
    private OssFileUtil ossFileUtil;

    /**
     * 校验并上传图片文件
     * @param file 图片文件
     * @return 文件OSS URL
     */
    @Override
    public String uploadImageFile(MultipartFile file) {
        FileTypeCheckUtil.validateFileType(file, FileType.IMAGE);
        String fileUrl = ossFileUtil.uploadFileToOss(file);
        if (!StringUtils.hasText(fileUrl)) {
            throw new UserException(UserResponseStatus.USER_FILE_UPLOAD_FAIL.getCode(),
                    UserResponseStatus.USER_FILE_UPLOAD_FAIL.getMessage());
        }
        return fileUrl;
    }
}
