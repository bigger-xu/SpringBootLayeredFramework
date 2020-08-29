/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 127.0.0.1:3306
 Source Schema         : base_freemarker

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 10/08/2020 23:45:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `uuid` varchar(64) DEFAULT NULL,
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型(1个人；2餐饮机构 ；3分销商)',
  `status` char(1) DEFAULT NULL COMMENT '用户状态(0锁定1正常)',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码校验规则md5(md5(password)+salt)',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐值',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avator` varchar(250) DEFAULT NULL COMMENT '用户头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后一次登录IP',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `province` int(11) DEFAULT NULL COMMENT '省',
  `city` int(11) DEFAULT NULL COMMENT '市',
  `district` int(11) DEFAULT NULL COMMENT '县',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `delete_flag` char(1) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
BEGIN;
INSERT INTO `admin_user` VALUES (14, '63f8bbb556824fde8df170796160f572', '2019-06-05 14:33:50', NULL, NULL, NULL, 'ROOT', '1', 'admin', 'c8fa7814cd7973c9a1f9b2328eba1708', '1620', '管理员', NULL, '2020-08-10 23:41:16', NULL, 76, NULL, NULL, NULL, '1', NULL, NULL, NULL, '0');
INSERT INTO `admin_user` VALUES (16, '16755c2d9b26415286802334150d84ab', '2019-06-08 17:43:12', NULL, '2019-06-09 16:05:40', NULL, 'PLAT', '1', 'test', 'fc2c3eb04243c18b7696ae0342dbbea7', '1119', '测试账号', NULL, '2019-06-09 16:07:17', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(64) DEFAULT NULL,
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '系统url',
  `parent_id` bigint(10) DEFAULT NULL COMMENT ' 父id 关联sys_menu.id',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识符',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `state` int(2) DEFAULT '0' COMMENT '是否显示（0：是 1：否）',
  `delete_flag` int(1) DEFAULT '0' COMMENT '是否删除,0=未删除，1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`parent_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (5, '74b47d50d5eb4aa78d931534bf663ed8', '2019-06-08 17:37:12', NULL, NULL, NULL, '系统管理', '', 0, '', 1, 1, 0);
INSERT INTO `menu` VALUES (6, '364c2ea87c7744eaa89de1cf14edafc6', '2019-06-08 17:37:37', NULL, NULL, NULL, '角色管理', '/role', 5, 'role', 1, 1, 0);
INSERT INTO `menu` VALUES (7, '9142720229a14feab4d6a1ab7119f8f9', '2019-06-08 17:37:59', NULL, NULL, NULL, '菜单管理', '/menu', 5, 'menu', 2, 1, 0);
INSERT INTO `menu` VALUES (8, '4cdae5161e514be287d1c5202725aafa', '2019-06-08 17:38:25', NULL, NULL, NULL, '用户管理', '/adminUser', 5, 'adminUser', 3, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for operation_logs
-- ----------------------------
DROP TABLE IF EXISTS `operation_logs`;
CREATE TABLE `operation_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL COMMENT 'UUID',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `request_url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `request_type` varchar(50) DEFAULT NULL COMMENT '请求方式',
  `request_params` varchar(255) DEFAULT NULL COMMENT '请求参数',
  `description` varchar(255) DEFAULT NULL COMMENT '日志描述',
  `leaves` tinyint(4) DEFAULT NULL COMMENT '日志级别',
  `run_time` bigint(20) DEFAULT NULL COMMENT '运行时长',
  `type` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `return_value` text COMMENT '返回数据',
  `ip_address` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of operation_logs
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(64) DEFAULT NULL,
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) DEFAULT NULL COMMENT '角色编码',
  `status` char(1) DEFAULT NULL COMMENT '状态1,0',
  `remark` varchar(255) DEFAULT NULL COMMENT '注释',
  `delete_flag` char(1) DEFAULT NULL COMMENT '删除状态(0否1是)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ename` (`code`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (41, 'd934e5357af84abca2c25a40eeae6df7', '2019-06-05 14:49:43', NULL, NULL, NULL, '超级管理员', 'ROOT', '1', NULL, '0');
INSERT INTO `role` VALUES (42, 'bce73e4e963d41119de75c146c0f0698', '2019-06-05 14:50:25', NULL, '2019-06-09 16:02:54', NULL, '管理员', 'PLAT', '1', NULL, '0');
INSERT INTO `role` VALUES (43, 'c894adf402b3486d9ef2a9cb8f81ffc5', '2019-06-08 17:45:09', NULL, '2019-06-09 16:02:59', NULL, '测试权限', 'TEST', '1', '', '0');
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(64) DEFAULT NULL,
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`role_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=965 DEFAULT CHARSET=utf8 COMMENT='菜单角色对应关系';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (949, NULL, '2019-06-05 14:52:32', 1, NULL, NULL, 41, 5);
INSERT INTO `role_menu` VALUES (950, NULL, '2019-06-05 14:52:46', 1, NULL, NULL, 41, 6);
INSERT INTO `role_menu` VALUES (951, NULL, '2019-06-08 17:39:50', 1, NULL, NULL, 41, 7);
INSERT INTO `role_menu` VALUES (952, NULL, '2019-06-08 17:39:56', 1, NULL, NULL, 41, 8);
INSERT INTO `role_menu` VALUES (960, '8349081b1d094d3ca3d7d60f60a590e0', '2019-06-09 16:02:54', NULL, NULL, NULL, 42, 5);
INSERT INTO `role_menu` VALUES (959, 'ab7911b516c444e4bb1150c46960553d', '2019-06-08 19:27:12', NULL, NULL, NULL, 45, 8);
INSERT INTO `role_menu` VALUES (958, 'c2a551bbd54e4fc5b054283c28413074', '2019-06-08 19:27:12', NULL, NULL, NULL, 45, 6);
INSERT INTO `role_menu` VALUES (957, '2bdca3fbe6e74704a9ef351a6ccd9f0a', '2019-06-08 19:27:12', NULL, NULL, NULL, 45, 5);
INSERT INTO `role_menu` VALUES (961, 'b2060d72639743f392630bbe2b7243fd', '2019-06-09 16:02:54', NULL, NULL, NULL, 42, 7);
INSERT INTO `role_menu` VALUES (962, 'fd794e90017c4137ad097480ddb8edbd', '2019-06-09 16:02:54', NULL, NULL, NULL, 42, 8);
INSERT INTO `role_menu` VALUES (963, 'cd96bb4037984acd91c2654930338e2d', '2019-06-09 16:02:59', NULL, NULL, NULL, 43, 5);
INSERT INTO `role_menu` VALUES (964, '9a600f8542e441a9b2c3a733fc509097', '2019-06-09 16:02:59', NULL, NULL, NULL, 43, 8);
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(64) DEFAULT NULL,
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user_id` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=958 DEFAULT CHARSET=utf8 COMMENT='用户角色对应关系';

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (949, NULL, '2019-06-05 14:51:21', 1, NULL, NULL, 41, 14);
INSERT INTO `role_user` VALUES (950, '2810c0e4f9b4476e8d67ea59dfdb3e30', '2019-06-09 15:55:34', NULL, NULL, NULL, 42, 18);
INSERT INTO `role_user` VALUES (951, '49def140d27643099936d78ad82786a7', '2019-06-09 15:59:35', NULL, NULL, NULL, 43, 19);
INSERT INTO `role_user` VALUES (955, 'a6bfb4213cbf448fa42a991a1fc159cb', '2019-06-09 16:02:06', NULL, NULL, NULL, 43, 20);
INSERT INTO `role_user` VALUES (956, '0cf95edaf6e34eaf903ac1ed2cbf82a8', '2019-06-09 16:05:40', NULL, NULL, NULL, 42, 16);
INSERT INTO `role_user` VALUES (957, '362febbab02f4f9682d2bc82f35c732c', '2019-11-13 08:09:27', NULL, NULL, NULL, 43, 21);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
