package org.epsda.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;
import org.epsda.base.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:35
 * Package Name: org.epsda.user.entity
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user", keepGlobalPrefix = true)
public class User extends BaseEntity {
    @TableId
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer gender;
    private String realName;
    private String idCard;
    private String avatarUrl;
    private String phone;
    private Long schoolId;
    private Long majorId;
    private Integer status;
}
