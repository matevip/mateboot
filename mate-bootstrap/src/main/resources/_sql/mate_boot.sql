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

 Date: 28/09/2023 10:26:18
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统授权表';

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
INSERT INTO `mate_sys_dept` VALUES (1695561846813188098, 0, '国人集团', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:20:21', 10000, '2023-08-27 07:55:55');
INSERT INTO `mate_sys_dept` VALUES (1695561907487989761, 1695561846813188098, '董事会', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:20:35', 1696500250245672962, '2023-09-17 21:30:48');
INSERT INTO `mate_sys_dept` VALUES (1695561973745410050, 1695561846813188098, '核心管理团队', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:20:51', 10000, '2023-08-27 06:22:32');
INSERT INTO `mate_sys_dept` VALUES (1695562444216295425, 1695561846813188098, '业务部门', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:22:43', 10000, '2023-08-27 07:55:43');
INSERT INTO `mate_sys_dept` VALUES (1695562560608231425, 1695562444216295425, '终端业务', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:23:11', 10000, '2023-08-27 06:30:38');
INSERT INTO `mate_sys_dept` VALUES (1695562645203148801, 1695562444216295425, '云业务', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:23:31', 10000, '2023-08-27 06:23:41');
INSERT INTO `mate_sys_dept` VALUES (1695562787973062657, 1695562444216295425, '企业业务', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:24:05', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695562834303344641, 1695562444216295425, '国际业务', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:24:16', 1701336117057458178, '2023-09-26 14:15:32');
INSERT INTO `mate_sys_dept` VALUES (1695562931690889218, 1695561846813188098, '研发中心', '', NULL, 4, NULL, 0, 10000, '2023-08-27 06:24:40', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695563766852313089, 1695562931690889218, '终端研发部', '', NULL, 1, NULL, 0, 10000, '2023-08-27 06:27:59', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695563841741611009, 1695562931690889218, '云计算研发部', '', NULL, 2, NULL, 0, 10000, '2023-08-27 06:28:17', 10000, '2023-08-27 06:28:35');
INSERT INTO `mate_sys_dept` VALUES (1695563894602424322, 1695562931690889218, '大数据研发部', '', NULL, 3, NULL, 0, 10000, '2023-08-27 06:28:29', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695564121799483394, 1695561846813188098, '财务部', '', NULL, 5, NULL, 0, 10000, '2023-08-27 06:29:23', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695564161506959362, 1695561846813188098, '法务部', '', NULL, 6, NULL, 0, 10000, '2023-08-27 06:29:33', NULL, NULL);
INSERT INTO `mate_sys_dept` VALUES (1695564226522865666, 1695561846813188098, '人力资源部', '', NULL, 7, NULL, 0, 10000, '2023-08-27 06:29:48', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_dict`;
CREATE TABLE `mate_sys_dict` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `dict_code` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) NOT NULL COMMENT '字典名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：停用   1：正常',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典类型';

-- ----------------------------
-- Records of mate_sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_dict` VALUES (1701080075010592769, 'sys_render', '性别', NULL, NULL, 1, 0, 1696500250245672962, '2023-09-11 11:47:49', 1696500250245672962, '2023-09-11 11:49:42');
INSERT INTO `mate_sys_dict` VALUES (1701080602846973953, 'sys_status', '状态', NULL, NULL, 1, 0, 1696500250245672962, '2023-09-11 11:49:55', 1696500250245672962, '2023-09-11 15:03:34');
INSERT INTO `mate_sys_dict` VALUES (1701081038022791170, 'sys_yes_no', '是否', NULL, NULL, 1, 0, 1696500250245672962, '2023-09-11 11:51:39', NULL, NULL);
INSERT INTO `mate_sys_dict` VALUES (1701128755340140545, 'sys_test', '测试', NULL, NULL, 0, 0, 1696500250245672962, '2023-09-11 15:01:15', 1696500250245672962, '2023-09-11 15:01:19');
INSERT INTO `mate_sys_dict` VALUES (1706939648338608129, 'login_type', '登录行为', NULL, NULL, 1, 0, 1696500250245672962, '2023-09-27 15:51:40', 1696500250245672962, '2023-09-27 15:52:04');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_dict_item`;
CREATE TABLE `mate_sys_dict_item` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `dict_id` bigint(20) NOT NULL COMMENT '字典类型ID',
  `dict_name` varchar(255) NOT NULL COMMENT '字典名称',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `dict_class` varchar(100) DEFAULT NULL COMMENT '字典样式',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：停用   1：正常',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典项';

-- ----------------------------
-- Records of mate_sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_dict_item` VALUES (1701080098603552770, 1701080075010592769, '男', '1', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:47:55', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701080116630671361, 1701080075010592769, '女', '2', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:47:59', 1696500250245672962, '2023-09-17 21:31:12');
INSERT INTO `mate_sys_dict_item` VALUES (1701080144589901826, 1701080075010592769, '未知', '0', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:48:06', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701080752021590017, 1701080602846973953, '正常', ' 1', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:50:30', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701080789069877250, 1701080602846973953, '禁用', '0', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:50:39', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701081083245776898, 1701081038022791170, '是', '1', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 11:51:49', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701125560916267009, 1701081038022791170, '否', '0', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 14:48:34', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701128803675299842, 1701128755340140545, '测试成功', '1', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 15:01:27', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1701128830300741633, 1701128755340140545, '测试失败', '0', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-11 15:01:33', 1696500250245672962, '2023-09-26 14:13:15');
INSERT INTO `mate_sys_dict_item` VALUES (1706939790307409922, 1706939648338608129, '登录成功', '0', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-27 15:52:14', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1706939884784107522, 1706939648338608129, '退出成功', '1', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-27 15:52:37', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1706939942103465985, 1706939648338608129, '验证码错误', '2', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-27 15:52:50', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1706939987229982721, 1706939648338608129, '客户端错误', '3', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-27 15:53:01', NULL, NULL);
INSERT INTO `mate_sys_dict_item` VALUES (1706940049469259777, 1706939648338608129, '账号密码错误', '4', NULL, NULL, 0, 1, 0, 1696500250245672962, '2023-09-27 15:53:16', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_login_log`;
CREATE TABLE `mate_sys_login_log` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  `address` varchar(32) DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(20) DEFAULT NULL COMMENT '操作系统',
  `status` tinyint(4) DEFAULT NULL COMMENT '登录状态  0：失败   1：成功',
  `business_type` tinyint(3) unsigned DEFAULT NULL COMMENT '操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of mate_sys_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_menu`;
CREATE TABLE `mate_sys_menu` (
  `id` bigint(20) NOT NULL COMMENT 'id',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单数据';

-- ----------------------------
-- Records of mate_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_menu` VALUES (1695808445619429377, 0, 'index', '首页', '/index', '', NULL, 0, 0, 'el-icon-home-filled', 0, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:40:15', 10000, '2023-08-27 22:40:57');
INSERT INTO `mate_sys_menu` VALUES (1695808640885252098, 1695808445619429377, 'dashboard', '控制台', '/dashboard', 'home/index', NULL, 0, 0, 'el-icon-office-building', 0, 1, '', 0, 0, 0, 0, 10000, '2023-08-27 22:41:01', 10000, '2023-08-27 22:42:20');
INSERT INTO `mate_sys_menu` VALUES (1695809033467912193, 1695808445619429377, 'usercenter', '账号信息', '/usercenter', 'userCenter/index', NULL, 0, 0, 'el-icon-user', 2, 0, 'new', 0, 0, 0, 0, 10000, '2023-08-27 22:42:35', 10000, '2023-08-27 22:43:02');
INSERT INTO `mate_sys_menu` VALUES (1695810131813523458, 0, 'system', '系统管理', '/system', '', 'sys:menu:list', 0, 0, 'el-icon-tools', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:46:57', 10000, '2023-08-27 23:29:28');
INSERT INTO `mate_sys_menu` VALUES (1695811066761633794, 1695810131813523458, 'config', '配置管理', '/config', '', NULL, 0, 0, 'el-icon-files', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:50:40', 10000, '2023-08-27 23:47:36');
INSERT INTO `mate_sys_menu` VALUES (1695811171111723010, 1695811066761633794, 'setting', '系统设置', '/system/setting', 'system/setting/index', 'sys:menu:list', 0, 0, 'el-icon-setting', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:51:04', 10000, '2023-08-27 23:23:48');
INSERT INTO `mate_sys_menu` VALUES (1695811373994401794, 1695811066761633794, 'dict', '数据字典', '/system/dict', 'system/dict/index', 'sys:menu:list', 0, 0, 'el-icon-reading', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:51:53', 1696500250245672962, '2023-09-17 21:23:02');
INSERT INTO `mate_sys_menu` VALUES (1695811916687007745, 1695811066761633794, 'task', '定时任务', '/system/task', 'system/task/index', NULL, 0, 0, 'el-icon-calendar', 3, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:54:02', 10000, '2023-08-27 22:54:30');
INSERT INTO `mate_sys_menu` VALUES (1695813352850251778, 1695810131813523458, 'authority', '权限管理', '/authority', '', 'sys:menu:list', 0, 0, 'el-icon-first-aid-kit', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 22:59:45', 1696500250245672962, '2023-09-26 21:18:47');
INSERT INTO `mate_sys_menu` VALUES (1695813590046531586, 1695813352850251778, 'user', '用户管理', '/system/user', 'system/user/index', 'sys:menu:list', 0, 0, 'el-icon-user', 1, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:00:41', 1696500250245672962, '2023-09-17 21:30:15');
INSERT INTO `mate_sys_menu` VALUES (1695813747962077186, 1695813352850251778, 'role', '角色管理', '/system/role', 'system/role/index', 'sys:menu:list', 0, 0, 'el-icon-notification', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:01:19', 1696500250245672962, '2023-09-17 21:26:44');
INSERT INTO `mate_sys_menu` VALUES (1695813988744486914, 1695813352850251778, 'menu', '菜单管理', '/system/menu', 'system/menu/index', NULL, 0, 0, 'el-icon-document', 3, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:02:16', 1696500250245672962, '2023-09-16 14:25:05');
INSERT INTO `mate_sys_menu` VALUES (1695814157506502657, 1695813352850251778, 'dept', '部门管理', '/system/dept', 'system/dept/index', 'sys:menu:list', 0, 0, 'el-icon-folder-opened', 4, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:02:57', 1696500250245672962, '2023-09-17 21:28:12');
INSERT INTO `mate_sys_menu` VALUES (1695820872713035778, 0, 'link', '外链管理', '', '', 'sys:menu:list', 0, 0, 'el-icon-operation', 2, 0, 'new', 0, 0, 0, 0, 10000, '2023-08-27 23:29:38', 10000, '2023-08-27 23:33:09');
INSERT INTO `mate_sys_menu` VALUES (1695822917742391297, 1695820872713035778, 'vue', 'Vue 官网', 'https://vuejs.org', '', 'sys:menu:list', 3, 0, 'sc-icon-vue', 0, 0, '官方', 0, 0, 0, 0, 10000, '2023-08-27 23:37:45', 10000, '2023-08-27 23:38:41');
INSERT INTO `mate_sys_menu` VALUES (1695823190003052546, 1695820872713035778, 'element-plus', 'Element Plus', 'https://element-plus.gitee.io/zh-CN/', '', 'sys:menu:list', 2, 0, 'el-icon-info-filled', 2, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:38:50', 1696500250245672962, '2023-09-13 21:27:54');
INSERT INTO `mate_sys_menu` VALUES (1695823619575279617, 0, 'about', '关于', '/other/about', 'other/about', 'sys:menu:list', 0, 0, 'el-icon-info-filled', 99, 0, '', 0, 0, 0, 0, 10000, '2023-08-27 23:40:32', 10000, '2023-08-27 23:41:20');
INSERT INTO `mate_sys_menu` VALUES (1701950609839640577, 1695820872713035778, 'mate_doc', 'Mate文档', 'http://localhost:8888/doc.html', '', 'sys:menu:list', 2, 0, 'el-icon-collection', 3, 0, 'new', 0, 0, 0, 0, 1696500250245672962, '2023-09-13 21:27:01', 1696500250245672962, '2023-09-13 21:31:39');
INSERT INTO `mate_sys_menu` VALUES (1702931503098490881, 1695813988744486914, NULL, '列表', NULL, NULL, 'sysMenu:list', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:24:44', 1696500250245672962, '2023-09-16 14:25:05');
INSERT INTO `mate_sys_menu` VALUES (1702931503127851010, 1695813988744486914, NULL, '更新', NULL, NULL, 'sysMenu:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:24:44', 1696500250245672962, '2023-09-16 14:25:05');
INSERT INTO `mate_sys_menu` VALUES (1702931503148822529, 1695813988744486914, NULL, '删除', NULL, NULL, 'sysMenu:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:24:44', 1696500250245672962, '2023-09-16 14:25:05');
INSERT INTO `mate_sys_menu` VALUES (1702931889494458369, 1695811066761633794, 'client', '客户端管理', '/system/client', 'system/client/index', NULL, 0, NULL, 'el-icon-cellphone', 4, 0, '', 0, 0, NULL, 0, 1696500250245672962, '2023-09-16 14:26:16', 1696500250245672962, '2023-09-16 14:28:15');
INSERT INTO `mate_sys_menu` VALUES (1702932236162072577, 1702931889494458369, NULL, '分页', NULL, NULL, 'sysClient:page', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:27:39', 1696500250245672962, '2023-09-16 14:28:15');
INSERT INTO `mate_sys_menu` VALUES (1702932236178849793, 1702931889494458369, NULL, '新增', NULL, NULL, 'sysClient:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:27:39', 1696500250245672962, '2023-09-16 14:28:15');
INSERT INTO `mate_sys_menu` VALUES (1702932236187238401, 1702931889494458369, NULL, '更新', NULL, NULL, 'sysClient:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:27:39', 1696500250245672962, '2023-09-16 14:28:15');
INSERT INTO `mate_sys_menu` VALUES (1702932236199821313, 1702931889494458369, NULL, '删除', NULL, NULL, 'sysClient:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-16 14:27:39', 1696500250245672962, '2023-09-16 14:28:15');
INSERT INTO `mate_sys_menu` VALUES (1702933281223331842, 1702931889494458369, 'undefined_sUBs', '未命名_sUBs', '', '', NULL, 0, NULL, NULL, 100, 0, NULL, NULL, NULL, NULL, 1, 1696500250245672962, '2023-09-16 14:31:48', 1696500250245672962, '2023-09-16 14:31:54');
INSERT INTO `mate_sys_menu` VALUES (1703399159463190530, 1695811373994401794, NULL, '列表', NULL, NULL, 'sysDict:list', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:23:02', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703399159479967745, 1695811373994401794, NULL, '新增', NULL, NULL, 'sysDict:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:23:02', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703399159492550657, 1695811373994401794, NULL, '更新', NULL, NULL, 'sysDict:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:23:02', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703399159505133570, 1695811373994401794, NULL, '删除', NULL, NULL, 'sysDict:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:23:02', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400092389646338, 1695813747962077186, NULL, '列表', NULL, NULL, 'sysRole:list', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:26:44', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400092402229249, 1695813747962077186, NULL, '新增', NULL, NULL, 'sysRole:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:26:44', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400092419006466, 1695813747962077186, NULL, '更新', NULL, NULL, 'sysRole:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:26:44', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400092431589378, 1695813747962077186, NULL, '删除', NULL, NULL, 'sysRole:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:26:44', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400461844914177, 1695814157506502657, NULL, '列表', NULL, NULL, 'sysDept:list', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:28:12', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400461853302785, 1695814157506502657, NULL, '新增', NULL, NULL, 'sysDept:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:28:12', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400461861691393, 1695814157506502657, NULL, '更新', NULL, NULL, 'sysDept:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:28:12', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400461870080002, 1695814157506502657, NULL, '删除', NULL, NULL, 'sysDept:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:28:12', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400975319998466, 1695813590046531586, NULL, '列表', NULL, NULL, 'sysUser:list', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:30:15', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400975328387074, 1695813590046531586, NULL, '新增', NULL, NULL, 'sysUser:add', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:30:15', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400975336775682, 1695813590046531586, NULL, '修改', NULL, NULL, 'sysUser:update', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:30:15', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1703400975345164289, 1695813590046531586, NULL, '删除', NULL, NULL, 'sysUser:del', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-17 21:30:15', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1706659313612791810, 1695810131813523458, 'audit', '系统审计', '/audit', '', 'sys:menu:list', 0, 0, 'el-icon-tools', 3, 0, '', 0, 0, 0, 0, 1696500250245672962, '2023-09-26 21:17:43', 1696500250245672962, '2023-09-26 21:18:38');
INSERT INTO `mate_sys_menu` VALUES (1706659632111460353, 1706659313612791810, 'online', '在线用户', '/system/online', 'system/online/index', 'sys:menu:list', 0, 0, 'el-icon-partly-cloudy', 1, 0, '', 0, 0, 0, 0, 1696500250245672962, '2023-09-26 21:18:59', 1696500250245672962, '2023-09-26 21:22:21');
INSERT INTO `mate_sys_menu` VALUES (1706925884155482114, 1706659313612791810, 'login-log', '登录日志', '/system/login', 'system/login/index', 'sys:menu:list', 0, 0, 'el-icon-tickets', 2, 0, '', 0, 0, 0, 0, 1696500250245672962, '2023-09-27 14:56:59', 1696500250245672962, '2023-09-27 14:59:38');
INSERT INTO `mate_sys_menu` VALUES (1706926550970052610, 1706925884155482114, NULL, '查询', NULL, NULL, 'sysLoginLog:page', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-27 14:59:38', NULL, NULL);
INSERT INTO `mate_sys_menu` VALUES (1706959421545914370, 1706659313612791810, 'operate-log', '操作日志', '/system/log', 'system/log/index', NULL, 0, NULL, 'el-icon-warning', 3, 0, '', 0, 0, NULL, 0, 1696500250245672962, '2023-09-27 17:10:15', 1696500250245672962, '2023-09-28 09:47:26');
INSERT INTO `mate_sys_menu` VALUES (1707210374434070529, 1706959421545914370, NULL, '分页', NULL, NULL, 'sysOperateLog:page', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 1696500250245672962, '2023-09-28 09:47:26', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_operate_log`;
CREATE TABLE `mate_sys_operate_log` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `module` varchar(100) DEFAULT NULL COMMENT '模块名',
  `name` varchar(100) DEFAULT NULL COMMENT '操作名',
  `req_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(20) DEFAULT NULL COMMENT '请求方法',
  `req_params` text COMMENT '请求参数',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `address` varchar(32) DEFAULT NULL COMMENT '登录地点',
  `user_agent` varchar(500) DEFAULT NULL COMMENT 'User Agent',
  `business_type` tinyint(4) DEFAULT NULL COMMENT '操作类型',
  `duration` int(11) NOT NULL COMMENT '执行时长',
  `status` tinyint(4) DEFAULT NULL COMMENT '操作状态  0：失败   1：成功',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `real_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `json_result` longtext COMMENT '返回消息',
  `error_msg` longtext COMMENT '返回消息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名',
  `os` varchar(20) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志';

-- ----------------------------
-- Records of mate_sys_operate_log
-- ----------------------------
BEGIN;
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
INSERT INTO `mate_sys_role` VALUES (1696042640069423105, '超级管理员', 'super_admin', '平台管理员，拥有所有数据可视权限', NULL, NULL, NULL, 1, 0, 10000, '2023-08-28 14:10:51', 10000, '2023-08-28 14:11:03');
INSERT INTO `mate_sys_role` VALUES (1701343371496370177, '普通管理员', 'common_admin', '普通管理员', NULL, NULL, NULL, 1, 0, 1696500250245672962, '2023-09-12 05:14:04', 1696500250245672962, '2023-09-25 17:29:39');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role_menu`;
CREATE TABLE `mate_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1706552965835276290 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色菜单';

-- ----------------------------
-- Records of mate_sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_role_menu` VALUES (1706552965801721857, 1701343371496370177, 1695808445619429377, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965805916161, 1701343371496370177, 1695808640885252098, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965805916162, 1701343371496370177, 1695809033467912193, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965805916163, 1701343371496370177, 1695811171111723010, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965805916164, 1701343371496370177, 1695811373994401794, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965810110465, 1701343371496370177, 1703399159492550657, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965810110466, 1701343371496370177, 1703399159479967745, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965810110467, 1701343371496370177, 1703399159505133570, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304769, 1701343371496370177, 1703399159463190530, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304770, 1701343371496370177, 1695813352850251778, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304771, 1701343371496370177, 1695813590046531586, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304772, 1701343371496370177, 1703400975345164289, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304773, 1701343371496370177, 1703400975319998466, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965814304774, 1701343371496370177, 1703400975336775682, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965818499073, 1701343371496370177, 1703400975328387074, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965818499074, 1701343371496370177, 1695813747962077186, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965818499075, 1701343371496370177, 1703400092402229249, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965822693377, 1701343371496370177, 1703400092419006466, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965822693378, 1701343371496370177, 1703400092389646338, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965822693379, 1701343371496370177, 1703400092431589378, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965826887682, 1701343371496370177, 1695813988744486914, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965826887683, 1701343371496370177, 1702931503098490881, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965826887684, 1701343371496370177, 1702931503148822529, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965826887685, 1701343371496370177, 1702931503127851010, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965826887686, 1701343371496370177, 1695814157506502657, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965831081986, 1701343371496370177, 1703400461853302785, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965831081987, 1701343371496370177, 1703400461844914177, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965831081988, 1701343371496370177, 1703400461861691393, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965831081989, 1701343371496370177, 1703400461870080002, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965831081990, 1701343371496370177, 1695810131813523458, 0);
INSERT INTO `mate_sys_role_menu` VALUES (1706552965835276289, 1701343371496370177, 1695811066761633794, 0);
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user`;
CREATE TABLE `mate_sys_user` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `super_admin` tinyint(4) DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户管理';

-- ----------------------------
-- Records of mate_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_user` VALUES (1696500250245672962, 'admin', '03c404e0317940b5bad7801b33aefb30e6bc3a1f6fddc2799ab7b05d75fbf316', '超管', 'https://cdn.maku.net/images/avatar.png', 0, '7333791@qq.com', '13900139000', 1695561846813188098, 1, 1, 0, 0, 10000, '2022-12-24 22:30:00', 10000, '2023-08-29 20:28:52');
INSERT INTO `mate_sys_user` VALUES (1701336117057458178, 'user', '989126c054da515a41ebe1c64ebb03c16981539f2c3e5012d33ca1f5aa0ffc36', '用户', '', 0, '7357288@qq.com', '13800138000', 1695564226522865666, 0, 1, NULL, 0, 1696500250245672962, '2023-09-12 04:45:14', 1696500250245672962, '2023-09-17 21:31:00');
COMMIT;

-- ----------------------------
-- Table structure for mate_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user_role`;
CREATE TABLE `mate_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1701950213586964483 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

-- ----------------------------
-- Records of mate_sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_user_role` VALUES (1701950213586964482, 1701343371496370177, 1701336117057458178, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
