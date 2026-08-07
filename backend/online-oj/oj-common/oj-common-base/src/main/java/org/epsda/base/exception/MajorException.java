package org.epsda.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息业务异常
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 18:00
 * Package Name: org.epsda.base.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MajorException extends ServiceException {
    public MajorException() {
    }

    public MajorException(String message) {
        super(message);
    }

    public MajorException(String code, String message) {
        super(code, message);
    }
}
