package org.epsda.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.epsda.base.exception.UserException;
import org.epsda.user.controller.dto.UserLoginDto;
import org.epsda.user.controller.vo.UserLoginVo;
import org.epsda.user.entity.SysUser;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.mapper.SysUserMapper;
import org.epsda.user.service.SystemUserService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户业务接口实现类
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 12:46
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 使用sa-token+jwt+redis实现管理员登录
     * @param userLoginDto 登录请求实体
     * @return 响应请求实体
     */
    @Override
    public UserLoginVo login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        // 判断用户是否存在
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getEmail, email));
        if (sysUser == null) {
            throw new UserException(UserResponseStatus.USER_NOT_FOUND.getCode(),
                    UserResponseStatus.USER_NOT_FOUND.getMessage());
        }

        // 校验密码是否一致(目前先明文处理)
        if (!sysUser.getPassword().equals(password)) {
            throw new UserException(UserResponseStatus.USER_EMAIL_PASSWORD_FAIL.getCode(),
                    UserResponseStatus.USER_NOT_FOUND.getMessage());
        }

        // 可以处理登录
        StpUtil.login(sysUser.getId());
        String tokenValue = StpUtil.getTokenValue();
        return UserLoginVo.builder()
                .userId(sysUser.getId())
                .username(sysUser.getUsername())
                .email(sysUser.getEmail())
                .avatarUrl(sysUser.getAvatarUrl())
                .token(tokenValue)
                .build();
    }

    /**
     * 管理员退出登录
     * @param userId 登录的管理员ID
     * @return 退出成功返回true，否则返回false
     */
    @Override
    public Boolean logout(Long userId) {
        StpUtil.logout(userId);
        return true;
    }
}
