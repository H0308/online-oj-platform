package org.epsda.user.service.impl;

import jakarta.annotation.Resource;
import org.epsda.user.mapper.UserMapper;
import org.epsda.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 普通用户接口实现类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 15:26
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据指定的学校ID获取有效的用户个数
     * @param schoolIds 学校ID
     * @return 学校ID与未被删除的用户个数映射Map
     */
    @Override
    public Map<Long, Long> listUserCountWithSchoolId(List<Long> schoolIds) {
        if (schoolIds.isEmpty()) {
            return new HashMap<>();
        }

        List<Map<String, Object>> countList = userMapper.countBySchoolIds(schoolIds);
        Map<Long, Long> userSchoolCountMap = new HashMap<>();
        for (Map<String, Object> result : countList) {
            Long schoolId = ((Number) result.get("schoolId")).longValue();
            Long count = ((Number) result.get("count")).longValue();
            userSchoolCountMap.put(schoolId, count);
        }

        return userSchoolCountMap;
    }

    /**
     * 根据指定的专业ID查询出有多少个关联用户
     * @param majorIds 专业ID
     * @return 专业ID与未被删除的用户个数映射Map
     */
    @Override
    public Map<Long, Long> listUserCountWithMajorId(List<Long> majorIds) {
        if (majorIds.isEmpty()) {
            return new HashMap<>();
        }

        List<Map<String, Object>> countList = userMapper.countByMajorIds(majorIds);
        Map<Long, Long> userMajorCountMap = new HashMap<>();
        for (Map<String, Object> result : countList) {
            Long majorId = ((Number) result.get("majorId")).longValue();
            Long count = ((Number) result.get("count")).longValue();
            userMajorCountMap.put(majorId, count);
        }

        return userMajorCountMap;
    }
}
