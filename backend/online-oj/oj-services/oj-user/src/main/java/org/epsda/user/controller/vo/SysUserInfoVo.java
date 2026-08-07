package org.epsda.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户信息响应实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 16:35
 * Package Name: org.epsda.user.controller.vo
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserInfoVo {
    private Long userId;
    private String username;
    private String email;
    private String avatarUrl;
}
