package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户个人信息修改请求实体（不包含用户密码）
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 15:49
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResetDto {
    @NotNull(message = "操作员ID不能为空")
    private Long currentUserId;
    @NotNull(message = "目标用户ID不能为空")
    private Long targetUserId;
}
