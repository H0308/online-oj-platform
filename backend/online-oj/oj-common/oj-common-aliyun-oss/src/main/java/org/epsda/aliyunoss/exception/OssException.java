package org.epsda.aliyunoss.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * Description: OSS异常，继承自运行时异常，不与业务异常挂钩
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 14:51
 * Package Name: org.epsda.aliyunoss.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OssException extends RuntimeException {
    private String code;
    private String message;

    public OssException() {
    }

    public OssException(String message) {
        super(message);
        this.message = message;
    }

    public OssException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
