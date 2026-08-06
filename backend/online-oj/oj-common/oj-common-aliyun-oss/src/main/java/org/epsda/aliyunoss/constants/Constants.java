package org.epsda.aliyunoss.constants;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: OSS工具常量定义
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 14:55
 * Package Name: org.epsda.aliyunoss.constants
 * Project Name: online-oj
 */
public record Constants() {
    // 支持的图片文件后缀
    public static final List<String> SUPPORTED_IMAGE_EXTENSIONS =
            List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp");
    // 支持的图片MIME类型
    public static final List<String> SUPPORTED_IMAGE_MIME =
            List.of("image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");
}
