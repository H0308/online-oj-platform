package org.epsda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.epsda.base.exception.SchoolException;
import org.epsda.user.controller.dto.SchoolAddDto;
import org.epsda.user.entity.School;
import org.epsda.user.enums.SchoolResponseStatus;
import org.epsda.user.mapper.SchoolMapper;
import org.epsda.user.service.SchoolService;
import org.springframework.stereotype.Service;

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
        School school = schoolMapper.selectOne(new LambdaQueryWrapper<School>()
                .eq(School::getSchoolCode, schoolCode));
        if (school != null) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_SAME_CODE_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_SAME_CODE_FAIL.getMessage());
        }

        String schoolChineseName = addDto.getSchoolChineseName();
        School newSchool = School.builder()
                .schoolChineseName(schoolChineseName).schoolCode(schoolCode).build();

        boolean insertRet = schoolMapper.insert(newSchool) == 1;
        if (!insertRet) {
            throw new SchoolException(SchoolResponseStatus.SCHOOL_ADD_FAIL.getCode(),
                    SchoolResponseStatus.SCHOOL_ADD_FAIL.getMessage());
        }

        return true;
    }
}
