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
    USER_EMAIL_PASSWORD_FAIL("USER_1001", "用户名或者密码错误"),
    USER_OLD_PASSWORD_WRONG("USER_1002", "原密码错误"),
    USER_OLD_PASSWORD_EQUAL("USER_1003", "旧密码与原密码相同"),
    USER_CONFIRM_PASSWORD_WRONG("USER_1004", "两次输入的密码不一致"),
    USER_UPDATE_FAIL("USER_1005", "用户数据更新失败"),
    USER_SAME_EMAIL_FAIL("USER_1006", "当前邮箱已存在"),
    USER_ADD_FAIL("USER_1007", "用户新增失败"),
    USER_PASSWORD_RESET_FAIL("USER_1008", "用户密码重置失败");

    private final String code;
    private final String message;
}
