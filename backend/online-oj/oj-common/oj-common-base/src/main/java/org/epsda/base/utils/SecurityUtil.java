package org.epsda.base.utils;

import cn.dev33.satoken.stp.StpUtil;
import org.epsda.base.enums.ResponseStatusCode;
import org.epsda.base.exception.ServiceException;

/**
 * Created with IntelliJ IDEA.
 * Description: 结合Sa-Token实现的安全工具类
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 15:59
 * Package Name: org.epsda.base.utils
 * Project Name: online-oj
 */
public class SecurityUtil {
    // 返回登录用户的ID
    public static long getLoginUserId() {
        return StpUtil.getLoginIdAsLong();
    }

    // 水平越权校验
    public static void checkHorizontalPermission(Long userId) {
        if (!userId.equals(getLoginUserId())) {
            throw new ServiceException(
                    ResponseStatusCode.HORIZONTAL_PERMISSION_NOT_ALLOWED.getCode(),
                    ResponseStatusCode.HORIZONTAL_PERMISSION_NOT_ALLOWED.getMessage());
        }
    }
}
