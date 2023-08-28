/*
 Navicat Premium Data Transfer

 Source Server         : locahost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mate_boot

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/08/2023 08:11:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mate_sys_client
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_client`;
CREATE TABLE `mate_sys_client` (
  `id` bigint NOT NULL COMMENT 'id',
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端id',
  `client_key` varchar(32) DEFAULT NULL COMMENT '客户端key',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `grant_type` varchar(255) DEFAULT NULL COMMENT '授权类型',
  `device_type` varchar(32) DEFAULT NULL COMMENT '设备类型',
  `active_timeout` int DEFAULT '1800' COMMENT 'token活跃超时时间',
  `timeout` int DEFAULT '604800' COMMENT 'token固定超时',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统授权表';

-- ----------------------------
-- Records of mate_sys_client
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_client` (`id`, `client_id`, `client_key`, `client_secret`, `grant_type`, `device_type`, `active_timeout`, `timeout`, `status`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, '0', 0, 1, '2023-08-21 16:32:53', 1, '2023-08-21 16:32:53');
INSERT INTO `mate_sys_client` (`id`, `client_id`, `client_key`, `client_secret`, `grant_type`, `device_type`, `active_timeout`, `timeout`, `status`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'android', 1800, 604800, '0', 0, 1, '2023-08-21 16:32:53', 1, '2023-08-21 16:32:53');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_dept`;
CREATE TABLE `mate_sys_dept` (
  `id` bigint NOT NULL,
  `pid` bigint DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '部门备注',
  `owner_user_id` bigint DEFAULT NULL COMMENT '部门负责人',
  `sort` int DEFAULT NULL COMMENT '排序',
  `version` int DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='部门';

-- ----------------------------
-- Records of mate_sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695561846813188098, 0, '国人集团', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:20:21', 10000, '2023-08-27 07:55:55');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695561907487989761, 1695561846813188098, '董事会', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:20:35', 10000, '2023-08-27 06:22:15');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695561973745410050, 1695561846813188098, '核心管理团队', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:20:51', 10000, '2023-08-27 06:22:32');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562444216295425, 1695561846813188098, '业务部门', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:22:43', 10000, '2023-08-27 07:55:43');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562560608231425, 1695562444216295425, '终端业务', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:23:11', 10000, '2023-08-27 06:30:38');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562645203148801, 1695562444216295425, '云业务', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:23:31', 10000, '2023-08-27 06:23:41');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562787973062657, 1695562444216295425, '企业业务', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:24:05', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562834303344641, 1695562444216295425, '国际业务', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:24:16', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695562931690889218, 1695561846813188098, '研发中心', '', NULL, 4, NULL, 0, 10000, '2023-08-27 06:24:40', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695563766852313089, 1695562931690889218, '终端研发部', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:27:59', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695563841741611009, 1695562931690889218, '云计算研发部', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:28:17', 10000, '2023-08-27 06:28:35');
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695563894602424322, 1695562931690889218, '大数据研发部', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:28:29', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695564121799483394, 1695561846813188098, '财务部', '', NULL, 5, NULL, 0, 10000, '2023-08-27 06:29:23', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695564161506959362, 1695561846813188098, '法务部', '', NULL, 6, NULL, 0, 10000, '2023-08-27 06:29:33', NULL, NULL);
INSERT INTO `mate_sys_dept` (`id`, `pid`, `name`, `remark`, `owner_user_id`, `sort`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695564226522865666, 1695561846813188098, '人力资源部', '', NULL, 7, NULL, 0, 10000, '2023-08-27 06:29:48', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_menu`;
CREATE TABLE `mate_sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `name` varchar(200) DEFAULT NULL COMMENT '别名',
  `title` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `component` varchar(200) DEFAULT NULL COMMENT '组件',
  `authority` varchar(500) DEFAULT NULL COMMENT '授权标识',
  `type` tinyint DEFAULT NULL COMMENT '类型   0：菜单   1：按钮   2：接口',
  `open_style` tinyint DEFAULT NULL COMMENT '打开方式   0：内部   1：外部',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int DEFAULT NULL COMMENT '排序',
  `affix` tinyint DEFAULT '0' COMMENT '是否固定',
  `tag` varchar(20) DEFAULT NULL COMMENT '标签',
  `hidden` tinyint DEFAULT NULL COMMENT '隐藏菜单 0：否 1：是',
  `hidden_breadcrumb` tinyint DEFAULT NULL COMMENT '隐藏面包屑 0：否 1：是',
  `version` int DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=1695828707324776450 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单数据';

-- ----------------------------
-- Records of mate_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695808445619429377, 0, 'index', '首页', '/index', '', NULL, 0, 0, 'el-icon-home-filled', 0, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:40:15', 10000, '2023-08-27 22:40:57');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695808640885252098, 1695808445619429377, 'dashboard', '控制台', '/dashboard', 'home/index', NULL, 0, 0, 'el-icon-office-building', 0, 1, '', 0, 0, 0, 0, 10000, '2023-08-27 22:41:01', 10000, '2023-08-27 22:42:20');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695809033467912193, 1695808445619429377, 'usercenter', '账号信息', '/usercenter', 'userCenter/index', NULL, 0, 0, 'el-icon-user', 2, 0, 'new', 0, 0, 0, 0, 10000, '2023-08-27 22:42:35', 10000, '2023-08-27 22:43:02');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695810131813523458, 0, 'system', '系统管理', '/system', '', 'sys:menu:list', 0, 0, 'el-icon-tools', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:46:57', 10000, '2023-08-27 23:29:28');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695811066761633794, 1695810131813523458, 'config', '配置管理', '/config', '', NULL, 0, 0, 'el-icon-files', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:50:40', 10000, '2023-08-27 23:47:36');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695811171111723010, 1695811066761633794, 'setting', '系统设置', '/system/setting', 'system/setting/index', 'sys:menu:list', 0, 0, 'el-icon-setting', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:51:04', 10000, '2023-08-27 23:23:48');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695811373994401794, 1695811066761633794, 'dic', '数据字典', '/system/dic', 'system/dic/index', NULL, 0, 0, 'el-icon-reading', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:51:53', 10000, '2023-08-27 23:25:55');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695811916687007745, 1695811066761633794, 'task', '定时任务', '/system/task', 'system/task/index', NULL, 0, 0, 'el-icon-calendar', 3, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:54:02', 10000, '2023-08-27 22:54:30');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695813352850251778, 1695810131813523458, 'authority', '权限管理', '/authority', '', NULL, 0, 0, 'el-icon-first-aid-kit', 3, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:59:45', 10000, '2023-08-27 23:00:35');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695813590046531586, 1695813352850251778, 'user', '用户管理', '/system/user', 'system/user/index', NULL, 0, 0, 'el-icon-user', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:00:41', 10000, '2023-08-27 23:01:12');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695813747962077186, 1695813352850251778, 'role', '角色管理', '/system/role', 'system/role/index', NULL, 0, 0, 'el-icon-notification', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:01:19', 10000, '2023-08-27 23:02:10');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695813988744486914, 1695813352850251778, 'menu', '菜单管理', '/system/menu', 'system/menu/index', NULL, 0, 0, 'el-icon-document', 3, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:02:16', 10000, '2023-08-27 23:05:16');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695814157506502657, 1695813352850251778, 'dept', '部门管理', '/system/dept', 'system/dept/index', NULL, 0, 0, 'el-icon-folder-opened', 4, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:02:57', 10000, '2023-08-27 23:03:34');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695820872713035778, 0, 'link', '外链管理', '', '', 'sys:menu:list', 0, 0, 'el-icon-operation', 2, 0, 'new', 0, 0, 0, 0, 10000, '2023-08-27 23:29:38', 10000, '2023-08-27 23:33:09');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695822917742391297, 1695820872713035778, 'vue', 'Vue 官网', 'https://vuejs.org', '', 'sys:menu:list', 3, 0, 'sc-icon-vue', 0, 0, '官方', 0, 0, 0, 0, 10000, '2023-08-27 23:37:45', 10000, '2023-08-27 23:38:41');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695823190003052546, 1695820872713035778, 'element-plus', 'Element Plus', 'https://element-plus.gitee.io/zh-CN/', '', 'sys:menu:list', 2, 0, 'el-icon-info-filled', 100, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:38:50', 10000, '2023-08-27 23:39:39');
INSERT INTO `mate_sys_menu` (`id`, `pid`, `name`, `title`, `path`, `component`, `authority`, `type`, `open_style`, `icon`, `sort`, `affix`, `tag`, `hidden`, `hidden_breadcrumb`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1695823619575279617, 0, 'about', '关于', '/other/about', 'other/about', 'sys:menu:list', 0, 0, 'el-icon-info-filled', 99, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:40:32', 10000, '2023-08-27 23:41:20');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role`;
CREATE TABLE `mate_sys_role` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint DEFAULT NULL COMMENT '数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据',
  `org_id` bigint DEFAULT NULL COMMENT '机构ID',
  `version` int DEFAULT NULL COMMENT '版本号',
  `status` tinyint DEFAULT '1' COMMENT '状态',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色管理';

-- ----------------------------
-- Records of mate_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_role` (`id`, `name`, `code`, `remark`, `data_scope`, `org_id`, `version`, `status`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, '超级管理员', 'super_admin', '平台管理员，拥有所有数据可视权限', NULL, NULL, 0, 1, 0, 10000, '2022-10-17 15:56:05', 10000, '2023-04-06 10:39:18');
INSERT INTO `mate_sys_role` (`id`, `name`, `code`, `remark`, `data_scope`, `org_id`, `version`, `status`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, '马上管理员', 'mashang', '马上管理员', NULL, NULL, 0, 1, 0, 10000, '2022-10-17 18:05:02', 10000, '2023-08-26 08:21:06');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role_menu`;
CREATE TABLE `mate_sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单';

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
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `org_id` bigint DEFAULT NULL COMMENT '机构ID',
  `super_admin` tinyint DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `version` int DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户管理';

-- ----------------------------
-- Records of mate_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_user` (`id`, `username`, `password`, `real_name`, `avatar`, `gender`, `email`, `mobile`, `org_id`, `super_admin`, `status`, `version`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (10000, 'admin', '03c404e0317940b5bad7801b33aefb30e6bc3a1f6fddc2799ab7b05d75fbf316', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', NULL, 1, 1, 0, 0, 10000, '2022-12-24 22:30:00', 10000, '2022-12-24 22:30:00');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user_role`;
CREATE TABLE `mate_sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色';

-- ----------------------------
-- Records of mate_sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
