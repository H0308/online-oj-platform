package org.epsda.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.epsda.base.enums.ResponseStatusCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 通用结果返回
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 13:22
 * Package Name: org.epsda.base.utils
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
public class ResultWrapper<T> {
    private String code;
    private String errMsg;
    private T data;

    // 正常情况
    public static <T> ResultWrapper<T> ok(T data) {
        return new ResultWrapper<>(ResponseStatusCode.OK.getCode(), "", data);
    }

    // 错误情况
    public static <T> ResultWrapper<T> fail(T data) {
        return new ResultWrapper<>(ResponseStatusCode.SYSTEM_INTERNAL_ERROR.getCode(),
                "", data);
    }

    public static <T> ResultWrapper<T> fail(String code, String errMsg) {
        return new ResultWrapper<>(code, errMsg, null);
    }

    public static <T> ResultWrapper<T> fail(String code, String errMsg, T data) {
        return new ResultWrapper<>(code, errMsg, data);
    }
}
