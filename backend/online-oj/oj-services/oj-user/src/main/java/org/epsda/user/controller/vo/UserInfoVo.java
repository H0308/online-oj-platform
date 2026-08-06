package org.epsda.user.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: 单个用户信息响应实体
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 14:07
 * Package Name: org.epsda.user.controller.vo
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Long userId;
    private String username;
    private String email;
    private String gender;
    private Boolean isRealNameAuth; // 是否实名认证（同时绑定姓名和身份证号）
    private String avatarUrl;
    private String phone;
    private String schoolName;
    private String majorName;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
