package org.epsda.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: 公共实体字段
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 10:52
 * Package Name: org.epsda.base.entity
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @TableLogic
    private Integer deleteFlag;
    private Long createBy;
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
