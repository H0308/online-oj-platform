package org.epsda.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户服务响应状态码
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 15:08
 * Package Name: org.epsda.user.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum UserResponseStatus {
    USER_OK("USER_0", "响应正常"),
    USER_NOT_FOUND("USER_1000", "用户不存在"),
    USER_EMAIL_PASSWORD_FAIL("USER_1001", "用户名或者密码错误");

    private final String code;
    private final String message;
}
