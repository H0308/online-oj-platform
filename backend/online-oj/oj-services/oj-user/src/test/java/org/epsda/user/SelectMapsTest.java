package org.epsda.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.epsda.user.entity.User;
import org.epsda.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: MP SelectMaps测试
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 15:56
 * Package Name: org.epsda.user
 * Project Name: online-oj
 */
@SpringBootTest
public class SelectMapsTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        List<Long> schoolIds = List.of(1L, 2L, 3L);
        // List<Map<String, Object>> countList = userMapper.selectMaps(
        //         new LambdaQueryWrapper<User>().in(User::getSchoolId, schoolIds));
        // System.out.println(countList);
        List<Map<String, Object>> countBySchoolIds = userMapper.countBySchoolIds(schoolIds);
        System.out.println(countBySchoolIds);
    }
}
