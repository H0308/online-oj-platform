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
    SCHOOL_EXISTED_FAIL("SCHOOL_1000", "当前学校已存在"),
    SCHOOL_ADD_FAIL("SCHOOL_1001", "学校信息新增失败"),
    SCHOOL_NOT_FOUND("SCHOOL_1002", "学校不存在"),
    SCHOOL_UPDATE_FAIL("SCHOOL_1003", "学校信息更新失败");

    private final String code;
    private final String message;
}
