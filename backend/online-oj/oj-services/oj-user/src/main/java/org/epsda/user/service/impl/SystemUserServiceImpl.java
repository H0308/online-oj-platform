package org.epsda.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;

import java.security.SecureRandom;

import org.epsda.base.domain.PageVo;
import org.epsda.base.exception.UserException;
import org.epsda.base.utils.RandomUtil;
import org.epsda.user.constants.Constants;
import org.epsda.user.controller.dto.SysUserAddDto;
import org.epsda.user.controller.dto.SysUserChangePasswordDto;
import org.epsda.user.controller.dto.SysUserLoginDto;
import org.epsda.user.controller.dto.SysUserResetPasswordDto;
import org.epsda.user.controller.vo.SysUserInfoVo;
import org.epsda.user.controller.vo.UserLoginVo;
import org.epsda.user.convert.SysUserConvert;
import org.epsda.user.entity.SysUser;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.manager.FileManager;
import org.epsda.user.mapper.SysUserMapper;
import org.epsda.user.service.SystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    @Resource
    private FileManager fileManager;
    @Resource
    private SysUserConvert sysUserConvert;

    private static final int MAX_PASSWORD_GENERATE_TIMES = 3;

    /**
     * 使用sa-token+jwt+redis实现管理员登录
     * @param sysUserLoginDto 登录请求实体
     * @return 响应请求实体
     */
    @Override
    public UserLoginVo login(SysUserLoginDto sysUserLoginDto) {
        String email = sysUserLoginDto.getEmail();
        String password = sysUserLoginDto.getPassword();

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
                    UserResponseStatus.USER_EMAIL_PASSWORD_FAIL.getMessage());
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

    /**
     * 管理员修改自己的密码
     * @param passwordDto 密码修改请求实体
     * @return 修改成功返回true，否则返回false
     */
    @Override
    public Boolean changePassword(SysUserChangePasswordDto passwordDto) {
        Long userId = passwordDto.getUserId();
        String oldPassword = passwordDto.getOldPassword();
        // 校验原始密码是否正确
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, userId));
        if (sysUser == null) {
            throw new UserException(UserResponseStatus.USER_NOT_FOUND.getCode(),
                    UserResponseStatus.USER_NOT_FOUND.getMessage());
        }

        // 当前是明文直接比较，后续需要新增Bcrypt加密
        if (!sysUser.getPassword().equals(oldPassword)) {
            throw new UserException(UserResponseStatus.USER_OLD_PASSWORD_WRONG.getCode(),
                    UserResponseStatus.USER_OLD_PASSWORD_WRONG.getMessage());
        }

        // 比较新密码和原密码是否相同
        String newPassword = passwordDto.getNewPassword();
        if (newPassword.equals(sysUser.getPassword())) {
            throw new UserException(UserResponseStatus.USER_OLD_PASSWORD_EQUAL.getCode(),
                    UserResponseStatus.USER_OLD_PASSWORD_EQUAL.getMessage());
        }
        // 比较新密码和确认密码是否相同
        String confirmPassword = passwordDto.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            throw new UserException(UserResponseStatus.USER_CONFIRM_PASSWORD_WRONG.getCode(),
                    UserResponseStatus.USER_CONFIRM_PASSWORD_WRONG.getMessage());
        }

        // 修改用户密码
        boolean updateRet = sysUserMapper.update(new SysUser(),
                new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, newPassword)) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    /**
     * 管理员新增用户接口
     * @param addDto 新增请求实体
     * @return 新增成功返回true，否则返回false
     */
    @Override
    public Boolean add(SysUserAddDto addDto) {
        String username = addDto.getUsername();
        // 使用用户名组成管理员邮箱
        String email = username + Constants.SYSTEM_USER_EMAIL_SUFFIX;
        SysUser oldUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, email));
        if (oldUser != null) {
            throw new UserException(UserResponseStatus.USER_SAME_EMAIL_FAIL.getCode(),
                    UserResponseStatus.USER_SAME_EMAIL_FAIL.getMessage());
        }
        // 随机生成密码
        String password = RandomUtil.generateRandomPassword();
        // 保存用户信息，密码目前明文
        SysUser sysUser = SysUser.builder().email(email)
                .username(username)
                .password(password).build();
        boolean insertRet = sysUserMapper.insert(sysUser) == 1;
        if (!insertRet) {
            throw new UserException(UserResponseStatus.USER_ADD_FAIL.getCode(),
                    UserResponseStatus.USER_ADD_FAIL.getMessage());
        }

        return true;
    }

    /**
     * 管理员重置用户密码接口
     * @param resetPasswordDto 重置密码请求实体
     * @return 重置成功返回true，否则返回false
     */
    @Override
    public Boolean resetPassword(SysUserResetPasswordDto resetPasswordDto) {
        Long targetUserId = resetPasswordDto.getTargetUserId();
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, targetUserId));
        if (sysUser == null) {
            throw new UserException(UserResponseStatus.USER_NOT_FOUND.getCode(),
                    UserResponseStatus.USER_NOT_FOUND.getMessage());
        }

        int times = 0;
        String newPassword = "";
        while (times < MAX_PASSWORD_GENERATE_TIMES) {
            newPassword = RandomUtil.generateRandomPassword();
            // 非重复密码则直接退出，不再继续生成
            if (!sysUser.getPassword().equals(newPassword)) {
                break;
            }
            times++;
        }

        // 生成随机新密码错误
        if (times >= MAX_PASSWORD_GENERATE_TIMES) {
            throw new UserException(UserResponseStatus.USER_PASSWORD_RESET_FAIL.getCode(),
                    UserResponseStatus.USER_PASSWORD_RESET_FAIL.getMessage());
        }

        // 目前直接明文保存
        boolean updateRet = sysUserMapper.update(new SysUser(),
                new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, targetUserId)
                .set(SysUser::getPassword, newPassword)) == 1;

        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    /**
     * 管理员用户上传头像文件，文件上传到阿里云OSS
     * @param userId 当前登录的用户
     * @param file 用户新头像文件
     * @return 新头像OSS URL
     */
    @Override
    public String uploadAvatar(Long userId, MultipartFile file) {
        String fileUrl = fileManager.uploadImageFile(file);
        boolean updateRet = sysUserMapper.update(new SysUser(),
                new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getAvatarUrl, fileUrl)) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return fileUrl;
    }

    /**
     * 分页展示管理员信息列表
     * @param currentPage 当前页码
     * @param pageSize 页面内容数量
     * @param queryString 查询内容，支持邮箱和用户名
     * @return 带分页的管理员信息列表
     */
    @Override
    public PageVo<SysUserInfoVo> list(Long currentPage, Long pageSize, String queryString) {
        Page<SysUser> page = new Page<>(currentPage, pageSize);
        Page<SysUser> sysUserPages = sysUserMapper.selectPage(page,
                new LambdaQueryWrapper<SysUser>()
                        .like(StringUtils.hasText(queryString), SysUser::getUsername, queryString)
                        .or(StringUtils.hasText(queryString))
                        .like(StringUtils.hasText(queryString), SysUser::getEmail, queryString));

        return PageVo.<SysUserInfoVo>builder()
                .currentPage(currentPage)
                .totalPages(sysUserPages.getPages())
                .totalCount(sysUserPages.getTotal())
                .totalRecords(sysUserConvert.toSysUserInfoVoList(sysUserPages.getRecords()))
                .build();
    }
}
