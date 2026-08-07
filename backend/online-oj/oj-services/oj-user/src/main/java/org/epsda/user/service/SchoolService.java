package org.epsda.user.service;

import org.epsda.base.domain.PageVo;
import org.epsda.user.controller.dto.SchoolAddDto;
import org.epsda.user.controller.dto.SchoolChangeDto;
import org.epsda.user.controller.vo.SchoolInfoVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息接口类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:43
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
public interface SchoolService {
    Boolean add(SchoolAddDto addDto);

    Boolean changeSchool(SchoolChangeDto changeDto);

    Integer batchDelete(List<Long> schoolIds);

    PageVo<SchoolInfoVo> list(Long currentPage, Long pageSize, String queryString);
}
