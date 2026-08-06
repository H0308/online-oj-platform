package org.epsda.user;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.epsda.user.entity.User;
import org.epsda.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description: mp防全表更新插件测试
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 16:49
 * Package Name: org.epsda.user
 * Project Name: online-oj
 */
@SpringBootTest
public class BlockAttackInnerInterceptorTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.update(new LambdaUpdateWrapper<User>().set(User::getPassword, 1111));
    }
}
