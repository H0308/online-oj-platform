package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员用户密码重置请求实体
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 13:19
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserResetPasswordDto {
    @NotNull(message = "操作员ID不能为空")
    private Long currentUserId;
    @NotNull(message = "目标用户ID不能为空")
    private Long targetUserId;
}
