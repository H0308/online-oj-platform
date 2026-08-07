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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
     * @param queryString 搜索框内容，目前只会用于检索用户名和邮箱
     * @param gender 性别，可以用于筛选
     * @param schoolId 学校ID，可以用于筛选
     * @param majorId 主修ID，可以用于筛选
     * @param status 用户状态，可以用于筛选
     * @return 带分页的用户信息列表
     */
    @Override
    public PageVo<UserInfoVo> list(Long currentPage, Long pageSize, Integer gender, String queryString,
                                Long schoolId, Long majorId, Integer status) {
        Page<User> page = new Page<>(currentPage, pageSize);
        Page<User> userPages = userMapper.selectPage(page,
                new LambdaQueryWrapper<User>()
                        .like(StringUtils.hasText(queryString), User::getUsername, queryString)
                        .like(StringUtils.hasText(queryString), User::getEmail, queryString)
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

    /**
     * 批量删除用户，虚拟删除
     * @param targetUserIds 用户ID列表
     * @return 成功删除的条目个数
     */
    @Override
    @Transactional
    public Integer batchDelete(List<Long> targetUserIds) {
        if (targetUserIds.isEmpty()) {
            return 0;
        }

        // 检测是否有约束条件限制删除，当前暂不实现

        return userMapper.deleteByIds(targetUserIds);
    }

    /**
     * 重置用户名
     * @param targetUserId 目标用户ID
     * @return 重置成功返回true，否则返回false
     */
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

    /**
     * 重置头像
     * @param targetUserId 目标用户ID
     * @return 重置成功返回true，否则返回false
     */
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

    /**
     * 重置密码
     * @param targetUserId 目标用户ID
     * @return 重置成功返回true，否则返回false
     */
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

    /**
     * 重置用户实名认证状态
     * @param targetUserId 目标用户ID
     * @return 重置成功返回true，否则返回false
     */
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
