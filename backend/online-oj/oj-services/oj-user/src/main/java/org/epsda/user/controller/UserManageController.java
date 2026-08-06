package org.epsda.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.utils.ResultWrapper;
import org.epsda.base.utils.SecurityUtil;
import org.epsda.user.controller.dto.UserAddDto;
import org.epsda.user.controller.vo.UserInfoVo;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.service.UserManageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "gender", required = false) Integer gender,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "school", required = false) String school,
            @RequestParam(value = "major", required = false) String major,
            @RequestParam(value = "status", required = false) Integer status) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                userManageService.list(currentPage, pageSize, username, gender, email,
                        school, major, status));
    }

    // 修改用户个人信息（不包括密码）

    // 删除用户

    // 批量删除用户

    // 重置用户密码

    // 封禁用户

    // 批量新增用户（需要提供具体模板）
}
