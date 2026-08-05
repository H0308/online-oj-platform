package org.epsda.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录响应实体
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 12:42
 * Package Name: org.epsda.user.controller.vo
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private Long userId;
    private String username;
    private String email;
    private String avatarUrl;
    private String token;
}
