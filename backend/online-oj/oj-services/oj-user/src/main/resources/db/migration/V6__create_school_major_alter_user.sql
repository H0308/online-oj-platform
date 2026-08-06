/**
 * Description: 创建学校信息和专业信息表，并将用户表school_name和major_name字段修改为school_id和major_id。目前学校和专业表放在用户数据库，后续如果有其他需求再考虑移出
 * Author: EPSDA
 * Date: 2026/8/6
 * Time: 17:31
 * Project Name: online-oj
 */
ALTER TABLE `tb_user` CHANGE COLUMN `school_name` `school_id` bigint COMMENT '用户所在学校ID';
ALTER TABLE `tb_user` CHANGE COLUMN `major_name` `major_id` bigint COMMENT '用户主专业ID';
CREATE TABLE IF NOT EXISTS `tb_school` (
    `id` BIGINT PRIMARY KEY COMMENT '主键，使用雪花算法生成唯一ID',
    `school_chinese_name` VARCHAR(255) NOT NULL COMMENT '学校中文名称，包括校区',
    `school_code` VARCHAR(50) NOT NULL UNIQUE COMMENT '学校编码，用于标识学校',
    `delete_flag` TINYINT DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除',
    `create_by` BIGINT COMMENT '创建用户ID',
    `update_by` BIGINT COMMENT '更新用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间戳',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，默认为当前时间戳，更新时以当前时间戳为准'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学校信息表';

CREATE TABLE IF NOT EXISTS `tb_major` (
    `id` BIGINT PRIMARY KEY COMMENT '主键，使用雪花算法生成唯一ID',
    `major_chinese_name` VARCHAR(255) NOT NULL COMMENT '专业中文名称',
    `major_code` VARCHAR(50) NOT NULL UNIQUE COMMENT '专业编码',
    `delete_flag` TINYINT DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除',
    `create_by` BIGINT COMMENT '创建用户ID',
    `update_by` BIGINT COMMENT '更新用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间戳',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，默认为当前时间戳，更新时以当前时间戳为准'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专业信息表';
