package org.epsda.user.convert;

import org.epsda.user.controller.vo.MajorInfoVo;
import org.epsda.user.entity.Major;
import org.mapstruct.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业对象转换器
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:34
 * Package Name: org.epsda.user.converter
 * Project Name: online-oj
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MajorConvert {

    // Major 实体转换为 MajorInfoVo
    @Mapping(source = "id", target = "majorId")
    MajorInfoVo toMajorInfoVo(Major major);

    // Major 列表转换为 MajorInfoVo 列表
    List<MajorInfoVo> toMajorInfoVoList(List<Major> majors);
}
