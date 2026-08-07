package org.epsda.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.epsda.user.entity.School;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息持久层
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:38
 * Package Name: org.epsda.user.mapper
 * Project Name: online-oj
 */
@Mapper
public interface SchoolMapper extends BaseMapper<School> {
}
