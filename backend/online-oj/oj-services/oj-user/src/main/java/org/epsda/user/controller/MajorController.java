package org.epsda.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.utils.ResultWrapper;
import org.epsda.base.utils.SecurityUtil;
import org.epsda.user.controller.dto.MajorAddDto;
import org.epsda.user.controller.dto.MajorChangeDto;
import org.epsda.user.controller.vo.MajorInfoVo;
import org.epsda.user.enums.MajorResponseStatus;
import org.epsda.user.service.MajorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息控制器
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:26
 * Package Name: org.epsda.user.controller
 * Project Name: online-oj
 */
@RestController
@RequestMapping("/major")
@Tag(name = "专业信息设置与获取接口")
public class MajorController {

    @Resource
    private MajorService majorService;

    // 新增专业
    @Operation(summary = "新增专业")
    @PostMapping("/add")
    public ResultWrapper<Boolean> add(@Validated @RequestBody MajorAddDto addDto) {
        SecurityUtil.checkHorizontalPermission(addDto.getUserId());
        return ResultWrapper.ok(MajorResponseStatus.MAJOR_OK.getCode(),
                majorService.add(addDto));
    }

    // 修改专业
    @Operation(summary = "修改专业")
    @PostMapping("/change-major")
    public ResultWrapper<Boolean> changeMajor(@Validated @RequestBody MajorChangeDto changeDto) {
        SecurityUtil.checkHorizontalPermission(changeDto.getUserId());
        return ResultWrapper.ok(MajorResponseStatus.MAJOR_OK.getCode(),
                majorService.changeMajor(changeDto));
    }

    // 单独/批量删除专业
    @Operation(summary = "单独/批量删除专业")
    @DeleteMapping("/batchDelete")
    public ResultWrapper<Integer> batchDelete(@RequestParam("userId") Long userId,
                                        @RequestParam("majorIds") List<Long> majorIds) {
        SecurityUtil.checkHorizontalPermission(userId);
        return ResultWrapper.ok(MajorResponseStatus.MAJOR_OK.getCode(),
                majorService.batchDelete(majorIds));
    }

    // 获取专业列表
    @Operation(summary = "获取专业列表")
    @GetMapping("/list")
    public ResultWrapper<PageVo<MajorInfoVo>> list(@RequestParam("currentPage") Long currentPage,
                                                    @RequestParam("pageSize") Long pageSize,
                        @RequestParam(value = "queryString", required = false) String queryString) {
        return ResultWrapper.ok(MajorResponseStatus.MAJOR_OK.getCode(),
                majorService.list(currentPage, pageSize, queryString));
    }
}
