package org.epsda.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.epsda.base.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * Description: 专业实体类
 * Author: EPSDA
 * Date: 2026/08/07
 * Time: 13:38
 * Package Name: org.epsda.user.entity
 * Project Name: online-oj
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "major", keepGlobalPrefix = true)
public class Major extends BaseEntity {
    @TableId
    private Long id;
    private String majorChineseName;
    private String majorCode;
}
