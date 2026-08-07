package org.epsda.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 学校信息响应实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 16:22
 * Package Name: org.epsda.user.controller.vo
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolInfoVo {
    private Long schoolId;
    private String schoolChineseName;
    private String schoolCode;
}
