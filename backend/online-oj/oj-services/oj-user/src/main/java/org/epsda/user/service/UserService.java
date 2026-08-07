package org.epsda.user.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 普通用户接口类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 15:26
 * Package Name: org.epsda.user.service
 * Project Name: online-oj
 */
public interface UserService {

    // 根据指定的学校ID查询出有多少个关联用户
    Map<Long, Long> listUserCountWithSchoolId(List<Long> schoolIds);

    // 根据指定的专业ID查询出有多少个关联用户
    Map<Long, Long> listUserCountWithMajorId(List<Long> majorIds);
}
