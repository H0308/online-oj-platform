package org.epsda.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.utils.ResultWrapper;
import org.epsda.base.utils.SecurityUtil;
import org.epsda.user.controller.dto.SchoolAddDto;
import org.epsda.user.controller.dto.SchoolChangeDto;
import org.epsda.user.controller.vo.SchoolInfoVo;
import org.epsda.user.enums.SchoolResponseStatus;
import org.epsda.user.service.SchoolService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息控制器
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:36
 * Package Name: org.epsda.user.controller
 * Project Name: online-oj
 */
@RestController
@RequestMapping("/school")
@Tag(name = "学校信息设置与获取接口")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    // 新增学校
    @Operation(summary = "新增学校")
    @PostMapping("/add")
    public ResultWrapper<Boolean> add(@Validated @RequestBody SchoolAddDto addDto) {
        SecurityUtil.checkHorizontalPermission(addDto.getUserId());
        return ResultWrapper.ok(SchoolResponseStatus.SCHOOL_OK.getCode(),
                schoolService.add(addDto));
    }

    // 修改学校
    @Operation(summary = "修改学校")
    @PostMapping("/change-school")
    public ResultWrapper<Boolean> changeSchool(@Validated @RequestBody SchoolChangeDto changeDto) {
        SecurityUtil.checkHorizontalPermission(changeDto.getUserId());
        return ResultWrapper.ok(SchoolResponseStatus.SCHOOL_OK.getCode(),
                schoolService.changeSchool(changeDto));
    }

    // 单独/批量删除学校
    @Operation(summary = "单独/批量删除学校")
    @DeleteMapping("/batchDelete")
    public ResultWrapper<Integer> batchDelete(@RequestParam("userId") Long userId,
                                        @RequestParam("schoolIds") List<Long> schoolIds) {
        SecurityUtil.checkHorizontalPermission(userId);
        return ResultWrapper.ok(SchoolResponseStatus.SCHOOL_OK.getCode(),
                schoolService.batchDelete(schoolIds));
    }

    // 获取学校列表
    @Operation(summary = "获取学校列表")
    @GetMapping("/list")
    public ResultWrapper<PageVo<SchoolInfoVo>> list(@RequestParam("currentPage") Long currentPage,
                                                    @RequestParam("pageSize") Long pageSize,
                        @RequestParam(value = "queryString", required = false) String queryString) {
        return ResultWrapper.ok(SchoolResponseStatus.SCHOOL_OK.getCode(),
                schoolService.list(currentPage, pageSize, queryString));
    }
}
