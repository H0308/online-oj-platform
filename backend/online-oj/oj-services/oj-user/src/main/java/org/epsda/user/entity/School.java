package org.epsda.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.epsda.base.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * Description:
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
@TableName(value = "school", keepGlobalPrefix = true)
public class School extends BaseEntity {
    @TableId
    private Long id;
    private String schoolChineseName;
    private String schoolCode;
}
