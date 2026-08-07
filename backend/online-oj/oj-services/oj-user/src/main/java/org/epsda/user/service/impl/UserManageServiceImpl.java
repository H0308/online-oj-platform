package org.epsda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.exception.UserException;
import org.epsda.base.utils.RandomUtil;
import org.epsda.user.constants.Constants;
import org.epsda.user.controller.dto.UserAddDto;
import org.epsda.user.controller.dto.UserInfoResetDto;
import org.epsda.user.controller.vo.UserInfoVo;
import org.epsda.user.convert.UserConvert;
import org.epsda.user.entity.User;
import org.epsda.user.enums.UserInfoResetType;
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

    /**
     * 新增普通用户
     * @param addDto 新增普通用户实体
     * @return 新增成功后返回true，否则返回false
     */
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

    /**
     * 获取所有普通用户信息列表
     * @param currentPage 当前页码
     * @param pageSize 当前页数据量
     * @param username 用户名，可以用于筛选
     * @param gender 性别，可以用于筛选
     * @param email 邮箱，可以用于筛选
     * @param schoolId 学校ID，可以用于筛选
     * @param majorId 主修ID，可以用于筛选
     * @param status 用户状态，可以用于筛选
     * @return 带分页的用户信息列表
     */
    @Override
    public PageVo<UserInfoVo> list(Long currentPage, Long pageSize,
                                String username, Integer gender, String email,
                                Long schoolId, Long majorId, Integer status) {
        Page<User> page = new Page<>(currentPage, pageSize);
        Page<User> userPages = userMapper.selectPage(page,
                new LambdaQueryWrapper<User>()
                        .like(StringUtils.hasText(username), User::getUsername, username)
                        .like(StringUtils.hasText(email), User::getEmail, email)
                        .eq(gender != null, User::getGender, gender)
                        .eq(schoolId != null, User::getSchoolId, schoolId)
                        .eq(majorId != null, User::getMajorId, majorId)
                        .eq(status != null, User::getStatus, status));

        return PageVo.<UserInfoVo>builder()
                .currentPage(currentPage)
                .totalPages(userPages.getPages())
                .totalCount(userPages.getTotal())
                .totalRecords(userConvert.toUserInfoVoList(userPages.getRecords()))
                .build();
    }

    /**
     * 根据重置类型对普通用户的信息进行重置
     * @param resetDto 重置请求实体
     * @param resetType 重置信息类型
     * @return 重置成功返回true，否则返回false
     */
    @Override
    public Boolean resetInfo(UserInfoResetDto resetDto, UserInfoResetType resetType) {
        Long targetUserId = resetDto.getTargetUserId();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, targetUserId));
        if (user == null) {
            throw new UserException(UserResponseStatus.USER_NOT_FOUND.getCode(),
                    UserResponseStatus.USER_NOT_FOUND.getMessage());
        }
        return switch (resetType) {
            case AVATAR -> resetAvatar(targetUserId);
            case USERNAME -> resetUsername(targetUserId);
            case PASSWORD -> resetPassword(targetUserId);
            case REAL_NAME_AUTH -> resetRealNameAuth(targetUserId);
        };
    }

    private Boolean resetUsername(Long targetUserId) {
        boolean updateRet = userMapper.update(new User(),
                new LambdaUpdateWrapper<User>()
                    .eq(User::getId, targetUserId)
                    .set(User::getUsername, RandomUtil.generateRandomUsername())) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    private Boolean resetAvatar(Long targetUserId) {
        boolean updateRet = userMapper.update(new User(),
                new LambdaUpdateWrapper<User>()
                        .eq(User::getId, targetUserId)
                        .set(User::getAvatarUrl, Constants.DEFAULT_AVATAR_URL)) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    private Boolean resetPassword(Long targetUserId) {
        boolean updateRet = userMapper.update(new User(),
                new LambdaUpdateWrapper<User>()
                        .eq(User::getId, targetUserId)
                        .set(User::getPassword,
                                RandomUtil.generateRandomPassword())) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    private Boolean resetRealNameAuth(Long targetUserId) {
        boolean updateRet = userMapper.update(new User(),
                new LambdaUpdateWrapper<User>()
                        .eq(User::getId, targetUserId)
                        .set(User::getRealName, null)
                        .set(User::getIdCard, null)) == 1;
        if (!updateRet) {
            throw new UserException(UserResponseStatus.USER_UPDATE_FAIL.getCode(),
                    UserResponseStatus.USER_UPDATE_FAIL.getMessage());
        }

        return true;
    }

}
