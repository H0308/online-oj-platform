package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 封禁用户请求实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 10:35
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchBanOpDto {
    @NotNull(message = "操作员ID不能为空")
    private Long currentUserId;
    @NotNull(message = "目标用户ID列表不能为空")
    private List<Long> targetUserIds;
}
