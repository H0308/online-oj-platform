package org.epsda.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:48
 * Package Name: org.epsda.user.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum SchoolResponseStatus {
    SCHOOL_OK("SCHOOL_0", "响应正常"),
    SCHOOL_SAME_CODE_FAIL("SCHOOL_1000", "当前学校代号已存在"),
    SCHOOL_ADD_FAIL("SCHOOL_1001", "学校新增失败");

    private final String code;
    private final String message;
}
