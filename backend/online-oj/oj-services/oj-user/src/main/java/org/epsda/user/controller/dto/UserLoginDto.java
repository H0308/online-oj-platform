package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录请求实体
 * Author: EPSDA
 * Date: 2026/08/04
 * Time: 11:50
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotNull(message = "邮箱不能为空")
    @Length(max = 50, message = "邮箱最长不超过50个字符")
    private String email;
    @NotNull(message = "密码不能为空")
    @Length(max = 255, message = "密码最长不超过255个字符")
    private String password;
}
