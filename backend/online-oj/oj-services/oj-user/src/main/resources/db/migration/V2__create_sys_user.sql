/*
* Description: 创建管理员端用户表
* Author: EPSDA
* Date: 2026/8/3
* Time: 10:29
* Project Name: online-oj
*/
-- 用户表
CREATE TABLE IF NOT EXISTS `tb_sys_user` (
    `id` BIGINT PRIMARY KEY COMMENT '主键，使用雪花算法生成唯一ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码，使用BCrypt加密存储',
    `avatar_url` VARCHAR(255) DEFAULT 'https://online-oj-platform-bucket.oss-cn-hangzhou.aliyuncs.com/default_avatar.png' COMMENT '用户头像',
    `delete_flag` TINYINT DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除',
    `create_by` BIGINT COMMENT '创建用户ID',
    `update_by` BIGINT COMMENT '更新用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间戳',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，默认为当前时间戳，更新时以当前时间戳为准'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员端-用户表';
