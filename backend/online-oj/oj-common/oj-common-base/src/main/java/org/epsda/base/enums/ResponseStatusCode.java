package org.epsda.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description: 通用响应状态码
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 13:24
 * Package Name: org.epsda.base.enums
 * Project Name: online-oj
 */
@Getter
@AllArgsConstructor
public enum ResponseStatusCode {
    OK("BASE_0", "正常"),
    SYSTEM_INTERNAL_ERROR("BASE_1000", "服务器内部错误"),
    RESOURCES_NOT_FOUND("BASE_1001", "资源不存在"),
    HORIZONTAL_PERMISSION_NOT_ALLOWED("BEAS_1002", "不允许使用他人账户");

    private final String code;
    private final String message;
}
