package org.epsda.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息业务异常
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 14:01
 * Package Name: org.epsda.base.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SchoolException extends ServiceException {
    public SchoolException() {
    }

    public SchoolException(String message) {
        super(message);
    }

    public SchoolException(String code, String message) {
        super(code, message);
    }
}
