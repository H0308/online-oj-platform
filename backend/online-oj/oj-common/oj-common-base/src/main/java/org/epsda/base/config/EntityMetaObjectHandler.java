package org.epsda.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.epsda.base.utils.SecurityUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: MyBatis Plus自动填充字段配置
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 10:57
 * Package Name: org.epsda.base.config
 * Project Name: online-oj
 */
@Component
@Slf4j
public class EntityMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy",
                Long.class, SecurityUtil.getLoginUserId());
        this.strictInsertFill(metaObject, "createTime",
                LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新人信息：{}", SecurityUtil.getLoginUserId());
        this.strictUpdateFill(metaObject, "updateBy",
                Long.class, SecurityUtil.getLoginUserId());
        this.strictUpdateFill(metaObject, "updateTime",
                LocalDateTime.class, LocalDateTime.now());
    }
}

