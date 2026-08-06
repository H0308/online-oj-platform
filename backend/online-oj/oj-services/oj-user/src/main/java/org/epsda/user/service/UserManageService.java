package org.epsda.user.service;

import org.epsda.base.domain.PageVo;
import org.epsda.user.controller.dto.UserAddDto;
import org.epsda.user.controller.vo.UserInfoVo;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员操作普通用户接口类
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:34
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
public interface UserManageService {
    Boolean add(UserAddDto addDto);

    PageVo<UserInfoVo> list(Long currentPage, Long pageSize,
                            String username, Integer gender, String email,
                            String school, String major, Integer status);
}
