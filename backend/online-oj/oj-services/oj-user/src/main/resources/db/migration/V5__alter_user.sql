/*
* Description:
* Author: EPSDA
* Date: 2026/8/6
* Time: 13:30
* Project Name: online-oj
*/
ALTER TABLE `tb_user` ADD COLUMN `password` varchar(255) NOT NULL COMMENT '用户密码' AFTER `email`;
ALTER TABLE `tb_user` MODIFY COLUMN `gender` tinyint COMMENT '用户性别，1-男，2-女';
ALTER TABLE `tb_user` MODIFY COLUMN `avatar_url` varchar(255) NOT NULL DEFAULT 'https://online-oj-platform-bucket.oss-cn-hangzhou.aliyuncs.com/default_avatar.png' COMMENT '用户头像';
ALTER TABLE `tb_user` MODIFY COLUMN `status` tinyint NOT NULL DEFAULT 0 COMMENT '用户封禁状态，0-未封禁，1-已封禁';
ALTER TABLE `tb_user` MODIFY COLUMN `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '删除标记，0-未删除，1-已删除';