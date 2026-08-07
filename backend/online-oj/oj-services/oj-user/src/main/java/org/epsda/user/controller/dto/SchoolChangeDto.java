package org.epsda.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息修改请求实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 14:26
 * Package Name: org.epsda.user.controller.dto
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolChangeDto {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotNull(message = "学校ID不能为空")
    private Long schoolId;
    @Length(max = 255, message = "学校中文名称不能超过255个字符")
    private String schoolChineseName;
    @Length(max = 50, message = "学校代号不能超过50个字符")
    private String schoolCode;
}
