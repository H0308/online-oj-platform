package org.epsda.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户服务异常
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 15:02
 * Package Name: org.epsda.base.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserException extends ServiceException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String code, String message) {
        super(code, message);
    }
}
