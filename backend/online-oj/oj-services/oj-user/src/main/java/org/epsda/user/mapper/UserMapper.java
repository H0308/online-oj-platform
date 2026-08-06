package org.epsda.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.epsda.user.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:37
 * Package Name: org.epsda.user.mapper
 * Project Name: online-oj
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
