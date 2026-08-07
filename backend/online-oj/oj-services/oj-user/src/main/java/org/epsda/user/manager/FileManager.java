package org.epsda.user.manager;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件Manager接口类，封装文件工具服务供业务调用
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 15:08
 * Package Name: org.epsda.user.manager
 * Project Name: online-oj
 */
public interface FileManager {
    String uploadImageFile(MultipartFile file);
}
