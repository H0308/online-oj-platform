package org.epsda.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.epsda.user.entity.Major;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息持久层
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:38
 * Package Name: org.epsda.user.mapper
 * Project Name: online-oj
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
}
