package org.epsda.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 11:03
 * Package Name: org.epsda.user.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum UserBanOpType {
    NORMAL(0, "用户未封禁"),
    BANNED(1, "用户已封禁");

    private final Integer code;
    private final String description;
}
