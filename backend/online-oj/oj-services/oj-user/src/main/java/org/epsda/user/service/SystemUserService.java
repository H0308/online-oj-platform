package org.epsda.user.service;

import org.epsda.base.domain.PageVo;
import org.epsda.user.controller.dto.SysUserAddDto;
import org.epsda.user.controller.dto.SysUserChangePasswordDto;
import org.epsda.user.controller.dto.SysUserLoginDto;
import org.epsda.user.controller.dto.SysUserResetPasswordDto;
import org.epsda.user.controller.vo.SysUserInfoVo;
import org.epsda.user.controller.vo.UserLoginVo;
import org.springframework.web.multipart.MultipartFile;

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
    UserLoginVo login(SysUserLoginDto sysUserLoginDto);

    Boolean logout(Long userId);

    Boolean changePassword(SysUserChangePasswordDto passwordDto);

    Boolean add(SysUserAddDto addDto);

    Boolean resetPassword(SysUserResetPasswordDto resetPasswordDto);

    String uploadAvatar(Long userId, MultipartFile file);

    PageVo<SysUserInfoVo> list(Long currentPage, Long pageSize, String queryString);

}
