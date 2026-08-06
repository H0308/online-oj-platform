package org.epsda.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 普通用户个人信息重置类型枚举
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 16:06
 * Package Name: org.epsda.user.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum UserInfoResetType {
    AVATAR(0, "头像重置"),
    PASSWORD(1, "密码重置"),
    USERNAME(2, "用户名重置"),
    REAL_NAME_AUTH(3, "实名认证重置");

    private final Integer code;
    private final String description;
}
