package org.epsda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.epsda.base.domain.PageVo;
import org.epsda.base.exception.MajorException;
import org.epsda.user.controller.dto.MajorAddDto;
import org.epsda.user.controller.dto.MajorChangeDto;
import org.epsda.user.controller.vo.MajorInfoVo;
import org.epsda.user.convert.MajorConvert;
import org.epsda.user.entity.Major;
import org.epsda.user.enums.MajorResponseStatus;
import org.epsda.user.mapper.MajorMapper;
import org.epsda.user.service.MajorService;
import org.epsda.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息接口实现类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:36
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Resource
    private MajorMapper majorMapper;
    @Resource
    private UserService userService;
    @Resource
    private MajorConvert majorConvert;

    /**
     * 新增专业
     * @param addDto 新增专业请求实体
     * @return 新增成功返回true，否则返回false
     */
    @Override
    public Boolean add(MajorAddDto addDto) {
        String majorCode = addDto.getMajorCode();
        String majorChineseName = addDto.getMajorChineseName();
        Major major = majorMapper.selectOne(new LambdaQueryWrapper<Major>()
                .eq(Major::getMajorCode, majorCode).or()
                .eq(Major::getMajorChineseName, majorChineseName));
        if (major != null) {
            throw new MajorException(MajorResponseStatus.MAJOR_EXISTED_FAIL.getCode(),
                    MajorResponseStatus.MAJOR_EXISTED_FAIL.getMessage());
        }

        Major newMajor = Major.builder()
                .majorChineseName(majorChineseName).majorCode(majorCode).build();

        boolean insertRet = majorMapper.insert(newMajor) == 1;
        if (!insertRet) {
            throw new MajorException(MajorResponseStatus.MAJOR_ADD_FAIL.getCode(),
                    MajorResponseStatus.MAJOR_ADD_FAIL.getMessage());
        }

        return true;
    }

    /**
     * 修改专业信息
     * @param changeDto 修改专业信息请求实体
     * @return 修改成功返回true，否则返回false
     */
    @Override
    public Boolean changeMajor(MajorChangeDto changeDto) {
        String majorChineseName = changeDto.getMajorChineseName();
        String majorCode = changeDto.getMajorCode();
        Long majorId = changeDto.getMajorId();

        if (!StringUtils.hasText(majorChineseName) &&
            !StringUtils.hasText(majorCode)) {
            return false;
        }

        Major major = majorMapper.selectOne(new LambdaQueryWrapper<Major>()
                .eq(Major::getId, majorId));
        if (major == null) {
            throw new MajorException(MajorResponseStatus.MAJOR_NOT_FOUND.getCode(),
                    MajorResponseStatus.MAJOR_NOT_FOUND.getMessage());
        }

        Major existedMajor = majorMapper.selectOne(new LambdaQueryWrapper<Major>()
                .eq(StringUtils.hasText(majorChineseName), Major::getMajorChineseName,
                        majorChineseName).or(StringUtils.hasText(majorCode))
                .eq(StringUtils.hasText(majorCode), Major::getMajorCode, majorCode));
        if (existedMajor != null) {
            throw new MajorException(MajorResponseStatus.MAJOR_EXISTED_FAIL.getCode(),
                    MajorResponseStatus.MAJOR_EXISTED_FAIL.getMessage());
        }

        boolean updateRet = majorMapper.update(new Major(), new LambdaUpdateWrapper<Major>()
                .eq(Major::getId, majorId)
                .set(StringUtils.hasText(majorChineseName), Major::getMajorChineseName,
                        majorChineseName)
                .set(StringUtils.hasText(majorCode), Major::getMajorCode, majorCode)) == 1;
        if (!updateRet) {
            throw new MajorException(MajorResponseStatus.MAJOR_UPDATE_FAIL.getCode(),
                    MajorResponseStatus.MAJOR_UPDATE_FAIL.getMessage());
        }

        return true;
    }

    /**
     * 批量删除专业信息，虚拟删除，如果专业已经被用户引用，则不能删除
     * @param majorIds 专业ID
     * @return 返回成功删除的专业个数
     */
    @Override
    public Integer batchDelete(List<Long> majorIds) {
        if (majorIds.isEmpty()) {
            return 0;
        }

        Map<Long, Long> userMajorCountMap =
                userService.listUserCountWithMajorId(majorIds);
        for (Long majorId : majorIds) {
            if (userMajorCountMap.containsKey(majorId) &&
                userMajorCountMap.get(majorId) > 0L) {
                throw new MajorException(MajorResponseStatus.MAJOR_WITH_USER_FAIL.getCode(),
                        MajorResponseStatus.MAJOR_WITH_USER_FAIL.getMessage());
            }
        }

        return majorMapper.deleteByIds(majorIds);
    }

    /**
     * 分页获取专业信息列表
     * @param currentPage 当前页码
     * @param pageSize 每页数据数量
     * @param queryString 查询内容，目前支持对应专业名称和专业代码
     * @return 专业信息分页数据
     */
    @Override
    public PageVo<MajorInfoVo> list(Long currentPage, Long pageSize, String queryString) {
        Page<Major> page = new Page<>(currentPage, pageSize);
        Page<Major> majorPages = majorMapper.selectPage(page,
                new LambdaQueryWrapper<Major>()
                        .like(StringUtils.hasText(queryString), Major::getMajorChineseName, queryString)
                        .or(StringUtils.hasText(queryString))
                        .like(StringUtils.hasText(queryString), Major::getMajorCode, queryString));

        return PageVo.<MajorInfoVo>builder()
                .currentPage(currentPage)
                .totalPages(majorPages.getPages())
                .totalCount(majorPages.getTotal())
                .totalRecords(majorConvert.toMajorInfoVoList(majorPages.getRecords()))
                .build();
    }
}
