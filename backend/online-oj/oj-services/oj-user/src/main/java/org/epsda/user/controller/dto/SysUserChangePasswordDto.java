package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户修改密码请求实体
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 9:43
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserChangePasswordDto {
    @NotNull(message = "用户密码不能为空")
    private Long userId;
    @NotNull(message = "用户原密码不能为空")
    @Length(max = 255, message = "密码不能超过255个字符")
    private String oldPassword;
    @NotNull(message = "用户新密码均为空")
    @Length(max = 255, message = "密码不能超过255个字符")
    private String newPassword;
    @NotNull(message = "用户确认密码不能为空")
    @Length(max = 255, message = "密码不能超过255个字符")
    private String confirmPassword;
}
