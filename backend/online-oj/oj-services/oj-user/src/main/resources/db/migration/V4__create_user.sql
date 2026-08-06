/**
 * Description: 创建普通用户表
 * Author: EPSDA
 * Date: 2026/8/6
 * Time: 11:03
 * Project Name: online-oj
 */
-- 普通用户表
CREATE TABLE IF NOT EXISTS `tb_user` (
    `id` BIGINT PRIMARY KEY COMMENT '主键，使用雪花算法生成唯一ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户昵称',
    `email` VARCHAR(50) NOT NULL UNIQUE COMMENT '邮箱',
    `gender` TINYINT NOT NULL COMMENT '用户性别，1-男，2-女',
    `real_name` VARCHAR(50) COMMENT '用户真实姓名',
    `id_card` VARCHAR(16) UNIQUE COMMENT '用户身份证号',
    `avatar_url` VARCHAR(255) DEFAULT 'https://online-oj-platform-bucket.oss-cn-hangzhou.aliyuncs.com/default_avatar.png' COMMENT '用户头像',
    `phone` VARCHAR(20) UNIQUE COMMENT '用户电话',
    `school_name` VARCHAR(20) COMMENT '用户所在学校名称',
    `major_name` VARCHAR(20) COMMENT '用户主专业名称',
    `status` TINYINT DEFAULT 0 COMMENT '用户封禁状态，0-未封禁，1-已封禁',
    `delete_flag` TINYINT DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除',
    `create_by` BIGINT COMMENT '创建用户ID',
    `update_by` BIGINT COMMENT '更新用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间戳',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，默认为当前时间戳，更新时以当前时间戳为准'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
