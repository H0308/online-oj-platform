/*
* Description: 专业中文名称增加唯一约束
* Author: EPSDA
* Date: 2026/8/7
* Time: 14:25
* Project Name: online-oj
*/
ALTER TABLE `tb_major` ADD UNIQUE INDEX `uk_major_chinese_name` (`major_chinese_name`);
