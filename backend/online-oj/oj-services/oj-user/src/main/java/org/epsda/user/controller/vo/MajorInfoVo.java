package org.epsda.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业信息响应实体
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 17:32
 * Package Name: org.epsda.user.controller.vo
 * Project Name: online-oj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MajorInfoVo {
    private Long majorId;
    private String majorChineseName;
    private String majorCode;
}
