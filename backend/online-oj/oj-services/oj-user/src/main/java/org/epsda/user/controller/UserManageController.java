package org.epsda.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.utils.ResultWrapper;
import org.epsda.base.utils.SecurityUtil;
import org.epsda.user.controller.dto.BatchBanOpDto;
import org.epsda.user.controller.dto.UserAddDto;
import org.epsda.user.controller.dto.UserInfoResetDto;
import org.epsda.user.controller.vo.UserInfoVo;
import org.epsda.user.enums.UserBanOpType;
import org.epsda.user.enums.UserInfoResetType;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.service.UserManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 11:48
 * Package Name: org.epsda.user.controller
 * Project Name: online-oj
 */
@RestController
@RequestMapping("/user-manage")
@Tag(name = "管理员管理普通用户接口")
public class UserManageController {

    @Resource
    private UserManageService userManageService;

    // 新增用户
    @Operation(summary = "新增用户")
    @PostMapping("/add")
    public ResultWrapper<Boolean> add(@Validated @RequestBody UserAddDto addDto) {
        SecurityUtil.checkHorizontalPermission(addDto.getUserId());
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.add(addDto));
    }

    // 获取用户信息列表
    @Operation(summary = "获取用户信息")
    @GetMapping("/list")
    public ResultWrapper<PageVo<UserInfoVo>> list(@RequestParam("currentPage") Long currentPage,
            @RequestParam("pageSize") Long pageSize,
            @RequestParam(value = "queryString", required = false) String queryString,
            @RequestParam(value = "gender", required = false) Integer gender,
            @RequestParam(value = "school", required = false) Long schoolId,
            @RequestParam(value = "major", required = false) Long majorId,
            @RequestParam(value = "status", required = false) Integer status) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.list(currentPage, pageSize, gender, queryString,
                        schoolId, majorId, status));
    }

    // 重置用户名
    @Operation(summary = "重置用户名")
    @PostMapping("/reset-username")
    public ResultWrapper<Boolean> resetUsername(@Validated @RequestBody UserInfoResetDto changeDto) {
        SecurityUtil.checkHorizontalPermission(changeDto.getCurrentUserId());
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.resetInfo(changeDto, UserInfoResetType.USERNAME));
    }

    // 重置用户头像
    @Operation(summary = "重置用户头像")
    @PostMapping("/reset-avatar")
    public ResultWrapper<Boolean> resetAvatar(@Validated @RequestBody UserInfoResetDto changeDto) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.resetInfo(changeDto, UserInfoResetType.AVATAR));
    }

    // 重置用户密码
    @Operation(summary = "重置用户密码")
    @PostMapping("/reset-password")
    public ResultWrapper<Boolean> resetPassword(@Validated @RequestBody UserInfoResetDto changeDto) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.resetInfo(changeDto, UserInfoResetType.PASSWORD));
    }

    // 重置用户实名状态
    @Operation(summary = "重置用户实名状态")
    @PostMapping("/reset-real-name-auth")
    public ResultWrapper<Boolean> resetRealNameAuth(@Validated @RequestBody UserInfoResetDto changeDto) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.resetInfo(changeDto, UserInfoResetType.REAL_NAME_AUTH));
    }

    // 独立/批量删除用户
    @Operation(summary = "独立/批量删除用户")
    @DeleteMapping("/batchDelete")
    public ResultWrapper<Integer> batchDelete(@RequestParam("currentUserId") Long currentUserId,
                                            @RequestParam("targetUserIds") List<Long> targetUserIds) {
        SecurityUtil.checkHorizontalPermission(currentUserId);
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.batchDelete(targetUserIds));
    }

    // 单独/批量封禁用户
    @Operation(summary = "单独/批量封禁用户")
    @PostMapping("/batchBan")
    public ResultWrapper<Integer> batchBan(@Validated @RequestBody BatchBanOpDto banDto) {
        SecurityUtil.checkHorizontalPermission(banDto.getCurrentUserId());
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.batchBanOp(banDto, UserBanOpType.BANNED));
    }

    // 单独/批量取消封禁用户
    @Operation(summary = "单独/批量取消封禁用户")
    @PostMapping("/batchUnBan")
    public ResultWrapper<Integer> batchUnBan(@Validated @RequestBody BatchBanOpDto banDto) {
        SecurityUtil.checkHorizontalPermission(banDto.getCurrentUserId());
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.batchBanOp(banDto, UserBanOpType.NORMAL));
    }

    // 批量新增用户（需要提供具体模板）
}
