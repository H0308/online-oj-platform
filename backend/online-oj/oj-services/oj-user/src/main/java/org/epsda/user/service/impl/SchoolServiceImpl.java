package org.epsda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.epsda.base.exception.SchoolException;
import org.epsda.user.controller.dto.SchoolAddDto;
import org.epsda.user.controller.dto.SchoolChangeDto;
import org.epsda.user.entity.School;
import org.epsda.user.enums.SchoolResponseStatus;
import org.epsda.user.mapper.SchoolMapper;
import org.epsda.user.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息接口实现类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:43
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public Boolean add(SchoolAddDto addDto) {
        String schoolCode = addDto.getSchoolCode();
        String schoolChineseName = addDto.getSchoolChineseName();
        School school = schoolMapper.selectOne(new LambdaQueryWrapper<School>()
                .eq(School::getSchoolCode, schoolCode).or()
                .eq(School::getSchoolChineseName, schoolChineseName));
        if (school != null) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_EXISTED_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_EXISTED_FAIL.getMessage());
        }

        School newSchool = School.builder()
                .schoolChineseName(schoolChineseName).schoolCode(schoolCode).build();

        boolean insertRet = schoolMapper.insert(newSchool) == 1;
        if (!insertRet) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_ADD_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_ADD_FAIL.getMessage());
        }

        return true;
    }

    @Override
    public Boolean changeSchool(SchoolChangeDto changeDto) {
        String schoolChineseName = changeDto.getSchoolChineseName();
        String schoolCode = changeDto.getSchoolCode();
        Long schoolId = changeDto.getSchoolId();

        // 当二者同时为空时，说明是异常修改，直接返回false
        // 但是不能单独判断，因为一次修改只会改动其中一个字段
        if (!StringUtils.hasText(schoolChineseName) &&
            !StringUtils.hasText(schoolCode)) {
            return false;
        }

        School school = schoolMapper.selectOne(new LambdaQueryWrapper<School>()
                .eq(School::getId, schoolId));
        if (school == null) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_NOT_FOUND.getCode(),
                    SchoolResponseStatus.SCHOOL_NOT_FOUND.getMessage());
        }

        // 判断是否与原始值相同，不同再进行修改
        School existedSchool = schoolMapper.selectOne(new LambdaQueryWrapper<School>()
                .eq(StringUtils.hasText(schoolChineseName), School::getSchoolChineseName,
                        schoolChineseName).or(StringUtils.hasText(schoolCode))
                .eq(School::getSchoolCode, schoolCode));
        if (existedSchool != null) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_EXISTED_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_EXISTED_FAIL.getMessage());
        }

        boolean updateRet = schoolMapper.update(new School(), new LambdaUpdateWrapper<School>()
                .eq(School::getId, schoolId)
                .set(StringUtils.hasText(schoolChineseName), School::getSchoolChineseName,
                        schoolChineseName)
                .set(StringUtils.hasText(schoolCode), School::getSchoolCode, schoolCode)) == 1;
        if (!updateRet) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_UPDATE_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_UPDATE_FAIL.getMessage());
        }

        return true;
    }
}
