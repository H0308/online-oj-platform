package org.epsda.aliyunoss.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epsda.base.exception.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 14:51
 * Package Name: org.epsda.aliyunoss.exception
 * Project Name: online-oj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OssException extends ServiceException {
    public OssException() {
    }

    public OssException(String message) {
        super(message);
    }

    public OssException(String code, String message) {
        super(code, message);
    }
}
