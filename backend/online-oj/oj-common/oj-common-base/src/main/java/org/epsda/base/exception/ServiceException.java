package org.epsda.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 业务异常类，后续所有业务具体异常都必须继承当前类
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 13:33
 * Package Name: org.epsda.base.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private String code;
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
