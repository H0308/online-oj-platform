package org.epsda.user.convert;

import org.epsda.user.controller.vo.UserInfoVo;
import org.epsda.user.entity.User;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户对象转换器
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 14:30
 * Package Name: org.epsda.user.converter
 * Project Name: online-oj
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {

    // User 实体转换为 UserInfoVo
    @Mapping(source = "id", target = "userId")
    // 使用convertGender方法将user.gender转换并设置到userInfoVo.gender中
    @Mapping(source = "gender", target = "gender", qualifiedByName = "convertGender")
    UserInfoVo toUserInfoVo(User user);

    // User 列表转换为 UserInfoVo 列表
    List<UserInfoVo> toUserInfoVoList(List<User> users);

    // 性别映射
    @Named("convertGender")
    default String convertGender(Integer gender) {
        if (gender == null) {
            return null;
        }
        return switch (gender) {
            case 1 -> "男";
            case 2 -> "女";
            default -> "未知";
        };
    }

    // 设置实名结果，根据用户是否同时填写了真实姓名和身份证进行判定
    @AfterMapping
    default void setRealNameAuth(User user, @MappingTarget UserInfoVo userInfoVo) {
        userInfoVo.setIsRealNameAuth(StringUtils.hasText(user.getRealName())
                && StringUtils.hasText(user.getIdCard()));
    }
}
