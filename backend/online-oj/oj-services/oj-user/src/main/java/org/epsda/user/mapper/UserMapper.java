package org.epsda.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.epsda.user.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 普通用户信息持久层
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:37
 * Package Name: org.epsda.user.mapper
 * Project Name: online-oj
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据学校ID统计有效用户数量
     * @param schoolIds 学校ID列表
     * @return 学校ID与用户数量的映射列表
     */
    List<Map<String, Object>> countBySchoolIds(@Param("schoolIds") List<Long> schoolIds);

    /**
     * 根据专业ID统计有效用户数量
     * @param majorIds 专业ID列表
     * @return 专业ID与用户数量的映射列表
     */
    List<Map<String, Object>> countByMajorIds(@Param("majorIds") List<Long> majorIds);
}
