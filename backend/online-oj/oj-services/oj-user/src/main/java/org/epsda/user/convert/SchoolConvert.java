package org.epsda.user.convert;

import org.epsda.user.controller.vo.SchoolInfoVo;
import org.epsda.user.entity.School;
import org.mapstruct.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校对象转换器
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 16:30
 * Package Name: org.epsda.user.converter
 * Project Name: online-oj
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolConvert {

    // School 实体转换为 SchoolInfoVo
    @Mapping(source = "id", target = "schoolId")
    SchoolInfoVo toSchoolInfoVo(School school);

    // School 列表转换为 SchoolInfoVo 列表
    List<SchoolInfoVo> toSchoolInfoVoList(List<School> schools);
}
