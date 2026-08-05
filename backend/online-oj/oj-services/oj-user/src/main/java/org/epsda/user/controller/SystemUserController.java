package org.epsda.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.epsda.base.utils.ResultWrapper;
import org.epsda.base.utils.SecurityUtil;
import org.epsda.user.controller.dto.UserLoginDto;
import org.epsda.user.controller.vo.UserLoginVo;
import org.epsda.user.enums.UserResponseStatus;
import org.epsda.user.service.SystemUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户控制器
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 11:30
 * Package Name: org.epsda.user.controller
 * Project Name: online-oj
 */
@RestController
@RequestMapping("/sys-user")
public class SystemUserController {

    @Resource
    private SystemUserService systemUserService;

    // 管理员登录
    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public ResultWrapper<UserLoginVo> login(@Validated @RequestBody UserLoginDto userLoginDto) {
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                systemUserService.login(userLoginDto));
    }

    // 管理员退出登录
    @Operation(summary = "管理员退出登录")
    @GetMapping("/logout")
    public ResultWrapper<Boolean> logout(@RequestParam("userId") Long userId) {
        SecurityUtil.checkHorizontalPermission(userId);
        return ResultWrapper.ok(UserResponseStatus.USER_OK.getCode(),
                systemUserService.logout(userId));
    }
}
