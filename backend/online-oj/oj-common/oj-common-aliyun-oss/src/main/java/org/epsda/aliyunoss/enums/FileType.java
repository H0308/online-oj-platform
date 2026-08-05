package org.epsda.aliyunoss.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件类型枚举
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 14:50
 * Package Name: org.epsda.aliyunoss.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum FileType {
    IMAGE("图片文件");

    private final String description;
}
