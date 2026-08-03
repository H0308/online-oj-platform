package org.epsda.base.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * Description:
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
    RESOURCES_NOT_FOUND("BASE_1001", "资源不存在");

    private final String code;
    private final String message;
}
