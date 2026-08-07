package org.epsda.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息响应状态枚举
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:33
 * Package Name: org.epsda.user.enums
 * Project Name: online-oj
 */
@Getter
@RequiredArgsConstructor
public enum MajorResponseStatus {
    MAJOR_OK("MAJOR_0", "响应正常"),
    MAJOR_EXISTED_FAIL("MAJOR_1000", "当前专业已存在"),
    MAJOR_ADD_FAIL("MAJOR_1001", "专业信息新增失败"),
    MAJOR_NOT_FOUND("MAJOR_1002", "专业不存在"),
    MAJOR_UPDATE_FAIL("MAJOR_1003", "专业信息更新失败"),
    MAJOR_WITH_USER_FAIL("MAJOR_1004", "专业存在使用用户"),
    MAJOR_DELETE_FAIL("MAJOR_1005", "专业信息删除失败");

    private final String code;
    private final String message;
}
