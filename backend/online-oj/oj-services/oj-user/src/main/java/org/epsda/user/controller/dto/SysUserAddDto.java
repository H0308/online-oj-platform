package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 管理员新增用户请求实体
 * Author: EPSDA
 * Date: 2026/08/05
 * Time: 10:35
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserAddDto {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotNull(message = "用户名不能为空")
    @Length(max = 50, message = "用户名不能超过50个字符")
    private String username;
}
