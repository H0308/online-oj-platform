package org.epsda.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epsda.base.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户实体类
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 10:50
 * Package Name: org.epsda.user.entity
 * Project Name: online-oj
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user", keepGlobalPrefix = true)
public class SysUser extends BaseEntity {
    @TableId
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
}
