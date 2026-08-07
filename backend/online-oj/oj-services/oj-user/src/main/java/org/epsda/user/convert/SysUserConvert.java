package org.epsda.user.convert;

import org.epsda.user.controller.vo.SysUserInfoVo;
import org.epsda.user.entity.SysUser;
import org.mapstruct.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户对象转换器
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 16:40
 * Package Name: org.epsda.user.converter
 * Project Name: online-oj
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserConvert {

    // SysUser 实体转换为 SysUserInfoVo
    @Mapping(source = "id", target = "userId")
    SysUserInfoVo toSysUserInfoVo(SysUser sysUser);

    // SysUser 列表转换为 SysUserInfoVo 列表
    List<SysUserInfoVo> toSysUserInfoVoList(List<SysUser> sysUsers);
}
