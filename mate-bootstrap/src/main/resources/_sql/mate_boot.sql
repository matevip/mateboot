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

 Date: 21/08/2023 22:48:29
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
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- Records of mate_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_user` VALUES (10000, 'admin', '123456', 'admin', 'https://cdn.mate.vip/images/avatar.png', 0, 'babamu@126.com', '13612345678', NULL, 1, 1, 0, 0, 10000, '2022-12-24 22:30:00', 10000, '2022-12-24 22:30:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
