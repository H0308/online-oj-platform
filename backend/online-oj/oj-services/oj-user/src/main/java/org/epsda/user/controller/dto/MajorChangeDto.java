package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息修改请求实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:31
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorChangeDto {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotNull(message = "专业ID不能为空")
    private Long majorId;
    @Length(max = 255, message = "专业中文名称不能超过255个字符")
    private String majorChineseName;
    @Length(max = 50, message = "专业代号不能超过50个字符")
    private String majorCode;
}
