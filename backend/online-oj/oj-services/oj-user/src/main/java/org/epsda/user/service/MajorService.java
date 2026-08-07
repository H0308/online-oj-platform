package org.epsda.user.service;

import org.epsda.base.domain.PageVo;
import org.epsda.user.controller.dto.MajorAddDto;
import org.epsda.user.controller.dto.MajorChangeDto;
import org.epsda.user.controller.vo.MajorInfoVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息接口类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:35
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
public interface MajorService {
    Boolean add(MajorAddDto addDto);

    Boolean changeMajor(MajorChangeDto changeDto);

    Integer batchDelete(List<Long> majorIds);

    PageVo<MajorInfoVo> list(Long currentPage, Long pageSize, String queryString);
}
