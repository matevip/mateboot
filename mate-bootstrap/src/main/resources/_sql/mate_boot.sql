/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : mate_boot

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/08/2023 21:28:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mate_sys_client
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_client`;
CREATE TABLE `mate_sys_client` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端id',
  `client_key` varchar(32) DEFAULT NULL COMMENT '客户端key',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `grant_type` varchar(255) DEFAULT NULL COMMENT '授权类型',
  `device_type` varchar(32) DEFAULT NULL COMMENT '设备类型',
  `active_timeout` int(11) DEFAULT '1800' COMMENT 'token活跃超时时间',
  `timeout` int(11) DEFAULT '604800' COMMENT 'token固定超时',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统授权表';

-- ----------------------------
-- Records of mate_sys_client
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_client` VALUES (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, '0', 0, 1, '2023-08-21 16:32:53', 1, '2023-08-21 16:32:53');
INSERT INTO `mate_sys_client` VALUES (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'android', 1800, 604800, '0', 0, 1, '2023-08-21 16:32:53', 1, '2023-08-21 16:32:53');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_dept`;
CREATE TABLE `mate_sys_dept` (
  `id` bigint(20) NOT NULL,
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '部门备注',
  `owner_user_id` bigint(20) DEFAULT NULL COMMENT '部门负责人',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门';

-- ----------------------------
-- Records of mate_sys_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_menu`;
CREATE TABLE `mate_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `name` varchar(200) DEFAULT NULL COMMENT '别名',
  `title` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `component` varchar(200) DEFAULT NULL COMMENT '组件',
  `authority` varchar(500) DEFAULT NULL COMMENT '授权标识',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型   0：菜单   1：按钮   2：接口',
  `open_style` tinyint(4) DEFAULT NULL COMMENT '打开方式   0：内部   1：外部',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `affix` tinyint(4) DEFAULT '0' COMMENT '是否固定',
  `tag` varchar(20) DEFAULT NULL COMMENT '标签',
  `hidden` tinyint(4) DEFAULT NULL COMMENT '隐藏菜单 0：否 1：是',
  `hidden_breadcrumb` tinyint(4) DEFAULT NULL COMMENT '隐藏面包屑 0：否 1：是',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='菜单数据';

-- ----------------------------
-- Records of mate_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_menu` VALUES (1, 0, 'index', '首页', '/index', NULL, NULL, 0, 0, 'el-icon-home-filled', 0, 0, NULL, 0, 0, 0, 0, 10000, '2022-10-07 23:47:48', 10000, '2022-10-07 23:47:48');
INSERT INTO `mate_sys_menu` VALUES (2, 1, 'dashboard', '控制台', '/dashboard', 'home/index', NULL, 0, 0, 'el-icon-office-building', 1, 1, NULL, 0, 0, 0, 0, 10000, '2022-10-07 23:47:48', 10000, '2022-10-07 23:47:48');
INSERT INTO `mate_sys_menu` VALUES (3, 1, 'usercenter', '账号信息', '/usercenter', 'userCenter/index', NULL, 0, 0, 'el-icon-user', 2, 0, 'new', 0, 0, 0, 0, 10000, '2022-10-07 23:47:48', 10000, '2022-10-07 23:47:48');
INSERT INTO `mate_sys_menu` VALUES (4, 0, 'about', '关于', '/other/about', 'other/about', NULL, 0, 0, 'el-icon-info-filled', 99, 0, '', 0, 0, 0, 0, 10000, NULL, NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (5, 0, 'setting', '配置管理', '/setting', NULL, NULL, 0, NULL, 'el-icon-tools', 3, 0, NULL, 0, 0, 0, 0, 10000, '2022-10-15 20:12:41', 10000, '2023-04-12 19:12:04');
INSERT INTO `mate_sys_menu` VALUES (6, 27, 'system', '系统设置', '/setting/system', 'setting/system/index', NULL, 0, 0, 'el-icon-setting', 1, 0, '99', 0, 0, 0, 0, 10000, NULL, 10000, '2023-04-12 19:13:22');
INSERT INTO `mate_sys_menu` VALUES (7, 5, 'authority', '权限管理', '/authority', NULL, NULL, 0, 0, 'el-icon-open', 1, 0, NULL, 0, 0, 0, 0, 10000, NULL, NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (8, 7, 'user', '用户管理', '/setting/user', 'setting/user/index', NULL, 0, 0, 'el-icon-user', 1, 0, NULL, 0, 0, 0, 0, 10000, NULL, NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (9, 7, 'role', '角色管理', '/setting/role', 'setting/role/index', NULL, 0, 0, 'el-icon-money', 1, 0, NULL, 0, 0, 0, 0, 10000, NULL, NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (10, 7, 'menu', '菜单管理', '/setting/menu', 'setting/menu/index', 'sys:menu:list', 0, 0, 'el-icon-operation', 1, 0, '', 0, 0, 0, 0, 10000, NULL, 10000, '2022-10-17 10:06:33');
INSERT INTO `mate_sys_menu` VALUES (11, 10, NULL, '新增', NULL, NULL, 'sys:menu:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, 0, 0, 10000, '2022-10-17 10:06:33', 10000, '2022-10-17 10:06:33');
INSERT INTO `mate_sys_menu` VALUES (18, 10, NULL, '删除', NULL, NULL, 'sys:menu:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, 0, 0, 10000, '2022-10-17 10:06:33', 10000, '2022-10-17 10:06:33');
INSERT INTO `mate_sys_menu` VALUES (19, 0, 'link', '外链功能', '/link', NULL, 'sys:menu:list', 0, NULL, 'el-icon-operation', 11, 0, 'new', 0, 0, 0, 0, 10000, '2022-10-16 10:38:35', 10000, '2023-04-12 15:27:55');
INSERT INTO `mate_sys_menu` VALUES (23, 19, 'element-plus', 'Element Plus', 'https://element-plus.gitee.io/zh-CN/', '', NULL, 2, NULL, 'el-icon-info-filled', 100, 0, '', 0, 0, 0, 0, 10000, '2022-10-16 10:41:11', 10000, '2023-04-13 14:09:17');
INSERT INTO `mate_sys_menu` VALUES (25, 7, 'org', '机构管理', '/setting/org', 'setting/org/index', NULL, 0, 0, 'el-icon-money', 2, 0, 'new', 0, 0, 0, 0, 10000, '2022-10-20 09:36:17', 10000, '2022-10-20 09:50:06');
INSERT INTO `mate_sys_menu` VALUES (27, 5, 'sys', '系统管理', '/sys', '', NULL, 0, NULL, 'el-icon-tools', 0, 0, NULL, 0, 0, 0, 0, 10000, '2023-04-12 13:43:43', 10000, '2023-04-12 15:37:37');
INSERT INTO `mate_sys_menu` VALUES (28, 27, 'dic', '数据字典', '/setting/dic', 'setting/dic/index', NULL, 0, NULL, 'el-icon-tools', 3, 0, NULL, 0, 0, 0, 0, 10000, '2023-04-12 13:46:58', 10000, '2023-04-12 13:50:19');
INSERT INTO `mate_sys_menu` VALUES (29, 27, 'task', '定时任务', '/setting/task', 'setting/task/index', NULL, 0, NULL, 'el-icon-calendar', 5, 0, '', 0, 0, 0, 0, 10000, '2023-04-12 13:56:23', 10000, '2023-04-12 15:26:06');
INSERT INTO `mate_sys_menu` VALUES (31, 19, 'vue', 'Vue', 'https://vuejs.org', '', NULL, 3, NULL, 'sc-icon-vue', 0, 0, '', 0, 0, 0, 0, 10000, '2023-04-13 14:11:43', 10000, '2023-04-13 14:12:16');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role`;
CREATE TABLE `mate_sys_role` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint(4) DEFAULT NULL COMMENT '数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色管理';

-- ----------------------------
-- Records of mate_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_role` VALUES (1, '超级管理员', 'super_admin', '平台管理员，拥有所有数据可视权限', NULL, NULL, 0, 1, 0, 10000, '2022-10-17 15:56:05', 10000, '2023-04-06 10:39:18');
INSERT INTO `mate_sys_role` VALUES (2, '马上管理员1', 'mashang', '马上管理员', NULL, NULL, 0, 1, 0, 10000, '2022-10-17 18:05:02', 10000, '2023-04-06 13:11:17');
INSERT INTO `mate_sys_role` VALUES (1695062677862240258, '11', '11', '11', NULL, NULL, NULL, 1, NULL, 10000, '2023-08-25 21:16:50', NULL, NULL);
INSERT INTO `mate_sys_role` VALUES (1695064808287662081, '22', '22', '22', NULL, NULL, NULL, 1, 0, 10000, '2023-08-25 21:25:18', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role_menu`;
CREATE TABLE `mate_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单';

-- ----------------------------
-- Records of mate_sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user`;
CREATE TABLE `mate_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `super_admin` tinyint(4) DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COMMENT='用户管理';

-- ----------------------------
-- Records of mate_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_user` VALUES (10000, 'admin', '03c404e0317940b5bad7801b33aefb30e6bc3a1f6fddc2799ab7b05d75fbf316', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', NULL, 1, 1, 0, 0, 10000, '2022-12-24 22:30:00', 10000, '2022-12-24 22:30:00');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user_role`;
CREATE TABLE `mate_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色';

-- ----------------------------
-- Records of mate_sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
