package org.epsda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.exception.UserException;
import org.epsda.base.utils.RandomUtil;
import org.epsda.user.constants.Constants;
import org.epsda.user.controller.dto.UserAddDto;
import org.epsda.user.controller.vo.UserInfoVo;
import org.epsda.user.convert.UserConvert;
import org.epsda.user.entity.User;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.mapper.UserMapper;
import org.epsda.user.service.UserManageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员操作普通用户接口实现类
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:34
 * Package Name: org.epsda.user.service.impl
 * Project Name: online-oj
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvert userConvert;

    @Override
    public Boolean add(UserAddDto addDto) {
        // 随机生成用户名（可以重复）
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();
        String email = username + Constants.USER_DEFAULT_EMAIL_SUFFIX;

        // 密码暂时明文保存
        User user = User.builder().username(username)
                .password(password).email(email)
                .build();

        boolean insertRet = userMapper.insert(user) == 1;
        if (!insertRet) {
            throw new UserException(UserResponseStatus.USER_ADD_FAIL.getCode(),
                    UserResponseStatus.USER_ADD_FAIL.getMessage());
        }

        return true;
    }

    @Override
    public PageVo<UserInfoVo> list(Long currentPage, Long pageSize,
                                String username, Integer gender, String email,
                                String school, String major, Integer status) {
        Page<User> page = new Page<>(currentPage, pageSize);
        Page<User> userPages = userMapper.selectPage(page,
                new LambdaQueryWrapper<User>()
                        .like(StringUtils.hasText(username), User::getUsername, username)
                        .like(StringUtils.hasText(email), User::getEmail, email)
                        .eq(gender != null, User::getGender, gender)
                        .like(StringUtils.hasText(school), User::getSchoolName, school)
                        .like(StringUtils.hasText(major), User::getMajorName, major)
                        .eq(status != null, User::getStatus, status));

        return PageVo.<UserInfoVo>builder()
                .currentPage(currentPage)
                .totalPages(userPages.getPages())
                .totalCount(userPages.getTotal())
                .totalRecords(userConvert.toUserInfoVoList(userPages.getRecords()))
                .build();
    }

}
