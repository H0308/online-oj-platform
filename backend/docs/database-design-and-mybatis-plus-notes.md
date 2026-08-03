# 数据库设计与 MyBatis Plus 使用笔记

本文档汇总了在线 OJ 平台后端关于数据库表设计、MyBatis Plus 实体继承映射、关联查询映射等方面的讨论和结论，供后续开发参考。

---

## 一、数据库表设计原则

当前项目的数据库表设计遵循以下原则：

1. **不使用物理外键**  
   表与表之间的关联关系由业务层维护，不在数据库层面创建 `FOREIGN KEY` 约束。这样可以提高数据导入、迁移和分库的灵活性。

2. **主键全部使用雪花算法生成唯一 ID**  
   所有业务表的主键均为 `bigint` 类型，不启用数据库自增（`auto_increment`），主键值由应用层通过雪花算法生成。

3. **所有业务表全部使用 `tb_` 开头**  
   业务表统一以 `tb_` 作为前缀，例如：`tb_user`、`tb_role`、`tb_permission`。

4. **字段和表名全部使用反引号包裹**  
   在 SQL 脚本中，表名和字段名统一使用反引号（`` ` ``）包裹，避免与 MySQL 关键字冲突，提高 SQL 的健壮性。

### 示例：用户表

```sql
CREATE TABLE IF NOT EXISTS `tb_user` (
    `id` BIGINT PRIMARY KEY COMMENT '主键，使用雪花算法生成唯一ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码，使用BCrypt加密存储',
    `avatar_url` VARCHAR(255) DEFAULT 'https://online-oj-platform-bucket.oss-cn-hangzhou.aliyuncs.com/default_avatar.png' COMMENT '用户头像',
    `delete_flag` TINYINT DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除',
    `create_by` BIGINT NOT NULL COMMENT '创建用户ID',
    `update_by` BIGINT NOT NULL COMMENT '更新用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间戳',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，默认为当前时间戳，更新时以当前时间戳为准'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员端-用户表';
```

---

## 二、MyBatis Plus 单表查询与继承映射

### 问题

实体类采用继承形式，例如 `User` 继承 `BaseEntity`：

```java
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", keepGlobalPrefix = true)
public class User extends BaseEntity {
    @TableId
    private Long id;
    private String username;
    private String password;
    private String avatar_url;
}
```

使用 MyBatis Plus 的 `BaseMapper` 做单表查询时，父类 `BaseEntity` 中的字段（如 `deleteFlag`、`createTime`、`updateTime`、`createBy`、`updateBy`）能否被正确填充？

### 结论

**可以。** MyBatis Plus 的 `TableInfo` 会递归解析当前类及其父类的非 `static` 字段，因此父类字段会被自动识别并映射。

### 注意事项

1. **字段命名需匹配**  
   数据库字段为下划线形式（如 `delete_flag`、`create_time`），实体类属性通常为驼峰形式（如 `deleteFlag`、`createTime`）。需要确保 MyBatis Plus 配置中开启了 `map-underscore-to-camel-case: true`。

2. **父类字段需要有 getter/setter**  
   父类 `BaseEntity` 也需要提供 getter/setter，或者使用 Lombok 的 `@Data` 等注解，否则 MyBatis 无法通过反射填充值。

3. **表前缀配置**  
   `@TableName(value = "user", keepGlobalPrefix = true)` 表示最终表名会拼接全局前缀（如 `tb_user`），需与 SQL 中的表名保持一致。

---

## 三、关联查询与继承映射

### 问题

如果实体类存在继承关系，关联查询（多表 JOIN）的结果是否还能直接映射到父类属性？

### 结论

**不一定，取决于映射方式。**

| 场景 | 父类字段是否自动映射 | 说明 |
|------|---------------------|------|
| `BaseMapper.selectXxx` 单表查询 | ✅ 自动映射 | MyBatis Plus 自动生成的 ResultMap 包含父类字段 |
| 手写 XML 关联查询 + 未 `extends` 父类 resultMap | ❌ 不会映射 | 手写 resultMap 不会自动继承父类字段映射 |
| 手写 XML 关联查询 + `extends` 父类 resultMap | ✅ 映射 | 通过 `extends` 继承公共 resultMap |
| 返回 DTO/VO | 不需要父类映射 | 直接按 DTO 属性配置映射即可 |

### 原因

MyBatis Plus 在单表查询时会自动构建一个包含当前类和父类所有字段的 `ResultMap`。但手写 XML 的关联查询时，你需要自己定义 `ResultMap`，MyBatis 不会自动把 `BaseEntity` 的字段加进来。

### 手写 XML 关联查询的正确做法

```xml
<!-- 父类通用 resultMap -->
<resultMap id="BaseResultMap" type="org.epsda.system.entity.BaseEntity">
    <id column="id" property="id"/>
    <result column="delete_flag" property="deleteFlag"/>
    <result column="create_time" property="createTime"/>
    <result column="update_time" property="updateTime"/>
    <result column="create_by" property="createBy"/>
    <result column="update_by" property="updateBy"/>
</resultMap>

<!-- 子类继承 -->
<resultMap id="UserWithRoleMap" type="org.epsda.system.entity.User" extends="BaseResultMap">
    <result column="username" property="username"/>
    <result column="role_name" property="roleName"/>
</resultMap>
```

---

## 四、关联查询方案选型建议

如果不想写 XML，常用的替代方案如下：

### 1. mybatis-plus-join 插件（MPJ）

适合简单的两表/三表 `LEFT JOIN` 场景，可以用 Lambda Wrapper 的方式完成关联查询。

**示例：**

```java
MPJLambdaWrapper<User> wrapper = JoinWrappers.lambda(User.class)
    .selectAll(User.class)
    .selectAs(Role::getRoleName, UserVO::getRoleName)
    .leftJoin(Role.class, Role::getId, User::getRoleId);

List<UserVO> list = userMapper.selectJoinList(UserVO.class, wrapper);
```

**优点：**
- 无需写 XML，保持 MyBatis Plus 的 Lambda 风格。
- 类型安全，重构方便。

**缺点：**
- 增加第三方依赖，版本需与 MyBatis Plus 版本匹配。
- 复杂查询（多级关联、子查询、聚合）不如 XML 灵活。

**适用场景：** 简单关联查询，追求开发效率。

### 2. `@Select` 注解

适合简单的关联 SQL，直接写在 Mapper 方法上，无需创建 XML 文件。

### 3. 多次单表查询 + 内存组装

先查主表，再批量查关联表，最后在代码里组装结果。需要注意 N+1 查询问题。

### 4. 数据库视图（View）

把关联查询结果封装成数据库视图，MyBatis Plus 直接按单表实体映射。

---

## 五、总结

1. **表设计**：不使用物理外键，主键用雪花算法生成，业务表统一 `tb_` 前缀，SQL 中表名/字段名用反引号包裹。
2. **单表查询**：继承 `BaseEntity` 的实体类，父类字段可以由 MyBatis Plus 自动映射。
3. **关联查询**：
   - 用 MyBatis Plus 自动生成的方式或 join 插件返回实体类时，父类字段可以映射。
   - 手写 XML 时，需要显式 `extends` 父类的 `resultMap`，否则父类字段无法自动映射。
4. **选型建议**：简单关联推荐 mybatis-plus-join 插件；复杂关联或需要精细优化时，推荐手写 XML。
