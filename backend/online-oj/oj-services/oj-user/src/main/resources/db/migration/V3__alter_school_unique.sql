/*
* Description: 学校中文名称增加唯一约束
* Author: EPSDA
* Date: 2026/8/7
* Time: 14:10
* Project Name: online-oj
*/
ALTER TABLE `tb_school` ADD UNIQUE INDEX `uk_school_chinese_name` (`school_chinese_name`);
