/*
* Description:
* Author: EPSDA
* Date: 2026/8/4
* Time: 14:58
* Project Name: online-oj
*/
ALTER TABLE `tb_sys_user` ADD COLUMN `email` varchar(50) NOT NULL COMMENT '邮箱' AFTER `username`;
CREATE UNIQUE INDEX `email` ON `tb_sys_user` (`email`);