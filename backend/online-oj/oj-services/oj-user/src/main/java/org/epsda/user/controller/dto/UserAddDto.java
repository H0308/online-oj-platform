package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 13:26
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
    @NotNull(message = "操作员ID不能为空")
    private Long userId;
}
