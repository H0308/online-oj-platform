package org.epsda.user.service;

import org.epsda.user.controller.dto.UserLoginDto;
import org.epsda.user.controller.vo.UserLoginVo;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户业务接口类
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 12:46
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
public interface SystemUserService {
    UserLoginVo login(UserLoginDto userLoginDto);

    Boolean logout(Long userId);
}
