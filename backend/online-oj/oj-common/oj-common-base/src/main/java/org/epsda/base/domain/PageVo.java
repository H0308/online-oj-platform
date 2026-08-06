package org.epsda.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: EPSDA
 * Date: 2026/08/06
 * Time: 14:11
 * Package Name: org.epsda.base.entity
 * Project Name: online-oj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageVo<T> {
    private Long currentPage; // 当前页码
    private Long totalPages; // 总页码
    private Long totalCount; // 内容总数量
    private List<T> totalRecords; // 所有具体内容
}
