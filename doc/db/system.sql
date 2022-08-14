/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : system

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 14/08/2022 18:16:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_authorization
-- ----------------------------
DROP TABLE IF EXISTS `system_authorization`;
CREATE TABLE `system_authorization` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '英文名字',
  `label` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '中文名字',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型，Menu，Button',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
  `status` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'url',
  `parent_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父级编码',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of system_authorization
-- ----------------------------
BEGIN;
INSERT INTO `system_authorization` VALUES (1, '系统管理', '系统管理', 'system', 'Menu', 'el-icon-s-operation', 'normal', NULL, 'admin', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_authorization` VALUES (2, '用户管理', '用户管理', 'system-user', 'Menu', 'el-icon-s-custom', 'normal', '/system/user', 'system', 'main/user/List', NULL, NULL, NULL, NULL);
INSERT INTO `system_authorization` VALUES (3, '角色管理', '角色管理', 'system-role', 'Menu', 'el-icon-view', 'normal', '/system/role', 'system', 'main/role/List', NULL, NULL, NULL, NULL);
INSERT INTO `system_authorization` VALUES (4, '权限管理', '权限管理', 'system-authorization', 'Menu', 'el-icon-document', 'normal', '/system/authorization', 'system', 'main/authorization/List', NULL, NULL, NULL, NULL);
INSERT INTO `system_authorization` VALUES (5, '用户查询', '用户查询', 'system-user-list', 'Button', '', 'normal', '/system/user/list', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (6, '用户添加', '用户添加', 'system-user-add', 'Button', '', 'normal', '/system/user/add', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (7, '用户修改', '用户修改', 'system-user-update', 'Button', '', 'normal', '/system/user/update', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (8, '用户删除', '用户删除', 'system-user-delete', 'Button', '', 'normal', '/system/user/delete', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (9, '批量启用', '批量启用', 'system-user-batchEnable', 'Button', '', 'normal', '/system/user/batchEnable', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (10, '批量禁用', '批量禁用', 'system-user-batchDisable', 'Button', '', 'normal', '/system/user/batchDisable', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (11, '批量删除', '批量删除', 'system-user-batchDelete', 'Button', '', 'normal', '/system/user/batchDelete', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (12, '分配角色', '分配角色', 'system-user-setRole', 'Button', '', 'normal', '/system/user/setRole', 'system-user', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (13, '分配角色保存', '分配角色保存', 'system-user-setRole-save', 'Button', '', 'normal', '/system/user/setRole/save', 'system-user-setRole', '', NULL, NULL, '2022-04-15 13:07:52', '2022-04-15 13:07:52');
INSERT INTO `system_authorization` VALUES (14, '查询', '查询', 'system-role-list', 'Button', '', 'normal', '/system/role/list', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (15, '添加', '添加', 'system-role-add', 'Button', '', 'normal', '/system/role/add', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (16, '修改', '修改', 'system-role-update', 'Button', '', 'normal', '/system/role/update', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (17, '删除', '删除', 'system-role-delete', 'Button', '', 'normal', '/system/role/delete', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (18, '批量启用', '批量启用', 'system-role-batchEnable', 'Button', '', 'normal', '/system/role/batchEnable', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (19, '批量禁用', '批量禁用', 'system-role-batchDisable', 'Button', '', 'normal', '/system/role/batchDisable', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (20, '批量删除', '批量删除', 'system-role-batchDelete', 'Button', '', 'normal', '/system/role/batchDelete', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (21, '分配权限', '分配权限', 'system-role-setAuth', 'Button', '', 'normal', '/system/user/setAuth', 'system-role', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (22, '分配权限保存', '分配权限保存', 'system-role-setAuth-save', 'Button', '', 'normal', '/system/role/setAuth/save', 'system-role-setAuth', '', NULL, NULL, '2022-04-15 13:12:17', '2022-04-15 13:12:17');
INSERT INTO `system_authorization` VALUES (23, '查询', '查询', 'system-authorization-list', 'Button', '', 'normal', '/system/authorization/list', 'system-authorization', '', NULL, NULL, '2022-04-15 13:15:38', '2022-04-15 13:15:38');
INSERT INTO `system_authorization` VALUES (24, '添加', '添加', 'system-authorization-save', 'Button', '', 'normal', '/system/authorization/save', 'system-authorization', '', NULL, NULL, '2022-04-15 13:15:38', '2022-04-15 13:15:38');
INSERT INTO `system_authorization` VALUES (25, '修改', '修改', 'system-authorization-update', 'Button', '', 'normal', '/system/authorization/update', 'system-authorization', '', NULL, NULL, '2022-04-15 13:15:38', '2022-04-15 13:15:38');
INSERT INTO `system_authorization` VALUES (26, '删除', '删除', 'system-authorization-delete', 'Button', '', 'normal', '/system/authorization/delete', 'system-authorization', '', NULL, NULL, '2022-04-15 13:15:38', '2022-04-15 13:15:38');
INSERT INTO `system_authorization` VALUES (36, '添加保存', NULL, 'system-authorization-add-save', 'Button', NULL, 'normal', NULL, 'system-authorization', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `system_authorization` VALUES (40, '数据字典', NULL, 'code', 'Menu', NULL, 'normal', NULL, 'system', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名字',
  `code` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
  `status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, '管理员', 'admin', 'normal', NULL, NULL, NULL, '2022-04-03 10:24:16');
INSERT INTO `system_role` VALUES (5, '普通用户', 'guest', 'normal', NULL, '游客', NULL, NULL);
INSERT INTO `system_role` VALUES (6, '客服', 'kefu', 'normal', NULL, '', NULL, NULL);
INSERT INTO `system_role` VALUES (7, '运营管理', 'yygl', 'normal', NULL, '运营管理员', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_role_authorization
-- ----------------------------
DROP TABLE IF EXISTS `system_role_authorization`;
CREATE TABLE `system_role_authorization` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `authorization_id` int DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of system_role_authorization
-- ----------------------------
BEGIN;
INSERT INTO `system_role_authorization` VALUES (1, 1, 1);
INSERT INTO `system_role_authorization` VALUES (2, 1, 2);
INSERT INTO `system_role_authorization` VALUES (3, 2, 1);
INSERT INTO `system_role_authorization` VALUES (4, 2, 2);
INSERT INTO `system_role_authorization` VALUES (5, 2, 3);
INSERT INTO `system_role_authorization` VALUES (6, 1, 3);
INSERT INTO `system_role_authorization` VALUES (7, 1, 4);
INSERT INTO `system_role_authorization` VALUES (8, 1, 5);
INSERT INTO `system_role_authorization` VALUES (9, 1, 6);
INSERT INTO `system_role_authorization` VALUES (10, 1, 7);
INSERT INTO `system_role_authorization` VALUES (11, 1, 8);
INSERT INTO `system_role_authorization` VALUES (12, 1, 9);
INSERT INTO `system_role_authorization` VALUES (13, 1, 10);
INSERT INTO `system_role_authorization` VALUES (14, 1, 11);
INSERT INTO `system_role_authorization` VALUES (15, 1, 12);
INSERT INTO `system_role_authorization` VALUES (16, 1, 13);
INSERT INTO `system_role_authorization` VALUES (17, 1, 14);
INSERT INTO `system_role_authorization` VALUES (18, 1, 15);
INSERT INTO `system_role_authorization` VALUES (19, 1, 16);
INSERT INTO `system_role_authorization` VALUES (20, 1, 17);
INSERT INTO `system_role_authorization` VALUES (21, 1, 18);
INSERT INTO `system_role_authorization` VALUES (22, 1, 19);
INSERT INTO `system_role_authorization` VALUES (23, 1, 20);
INSERT INTO `system_role_authorization` VALUES (24, 1, 21);
INSERT INTO `system_role_authorization` VALUES (25, 1, 22);
INSERT INTO `system_role_authorization` VALUES (26, 1, 23);
INSERT INTO `system_role_authorization` VALUES (27, 1, 24);
INSERT INTO `system_role_authorization` VALUES (28, 1, 25);
INSERT INTO `system_role_authorization` VALUES (29, 1, 26);
INSERT INTO `system_role_authorization` VALUES (30, 1, 27);
INSERT INTO `system_role_authorization` VALUES (31, 1, 28);
INSERT INTO `system_role_authorization` VALUES (32, 1, 29);
INSERT INTO `system_role_authorization` VALUES (33, 7, 1);
INSERT INTO `system_role_authorization` VALUES (34, 7, 2);
INSERT INTO `system_role_authorization` VALUES (36, 5, 3);
INSERT INTO `system_role_authorization` VALUES (37, 5, 1);
INSERT INTO `system_role_authorization` VALUES (46, 6, 1);
INSERT INTO `system_role_authorization` VALUES (47, 6, 2);
INSERT INTO `system_role_authorization` VALUES (48, 6, 5);
INSERT INTO `system_role_authorization` VALUES (49, 6, 6);
INSERT INTO `system_role_authorization` VALUES (50, 6, 7);
INSERT INTO `system_role_authorization` VALUES (51, 6, 10);
INSERT INTO `system_role_authorization` VALUES (52, 6, 12);
INSERT INTO `system_role_authorization` VALUES (53, 6, 3);
INSERT INTO `system_role_authorization` VALUES (54, 6, 4);
INSERT INTO `system_role_authorization` VALUES (55, 6, 23);
INSERT INTO `system_role_authorization` VALUES (56, 6, 24);
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `phone_no` varchar(22) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `secret_key` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密钥',
  `status` varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `login_fail_count` int DEFAULT NULL COMMENT '登录失败次数',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, 'admin', '$2a$10$xtFQGEy5jfh4s.5ZeV1SmuleRX9LFK89.1GV7OX2h8SBMd.mI5eC.', '1111111111', NULL, 'normal', 0, '2022-06-19 16:27:40', 'ddd', '管理员', '2022-06-19 16:27:27');
INSERT INTO `system_user` VALUES (2, 'guest', '$2a$10$xtFQGEy5jfh4s.5ZeV1SmuleRX9LFK89.1GV7OX2h8SBMd.mI5eC.', '1111111111', NULL, 'normal', NULL, '2022-06-19 16:27:44', '访客', '访客', '2022-06-19 16:27:32');
INSERT INTO `system_user` VALUES (3, 'test', '111111', '111111111', NULL, 'normal', NULL, '2022-06-19 16:27:48', 'dddd', NULL, '2022-06-19 16:27:35');
INSERT INTO `system_user` VALUES (4, 'aa', '$2a$10$g99ZBlcGzYBQYn4tYRKIXOLR59vBmLZTNK4XlW2YpPITilY3BRN6a', '22222222222', NULL, 'normal', NULL, '2022-06-19 20:36:07', 'dsfds', NULL, '2022-06-19 20:36:07');
INSERT INTO `system_user` VALUES (5, 'bb', '$2a$10$eUwcgOdSeVy6iZF4JB0gyOWjQRmn5BHO51ImGfNrsrunMP27Xrrn2', '11111111111', NULL, 'disable', NULL, '2022-06-19 20:36:58', '1111', NULL, '2022-06-19 20:36:58');
INSERT INTO `system_user` VALUES (6, 'cc', '$2a$10$1KLbqO6KcOVB7zIOeS1CLe3rOKgbtZw7NQOp3o/6ub6iSDYOb/Ih.', '2222222222', NULL, 'normal', NULL, '2022-06-19 20:38:50', '收拾收拾', NULL, '2022-06-19 20:38:50');
INSERT INTO `system_user` VALUES (7, 'dd', '$2a$10$b5yBBocDAASYuokwAySEsuY3sKPVNr1ojUrGNhGWH8VFYpwiwGZUm', '44444444444', NULL, 'disable', NULL, '2022-06-19 20:40:25', '3333333', NULL, '2022-06-19 20:40:25');
INSERT INTO `system_user` VALUES (8, 'ee', '$2a$10$H7F3ID6IpIrBSjNJbXjj/.zXzmrS6AleW35UDgVq8x/nmvITsOERu', NULL, NULL, 'normal', NULL, '2022-06-19 20:41:31', NULL, NULL, '2022-06-19 20:41:31');
INSERT INTO `system_user` VALUES (9, 'ff', '$2a$10$CtwqAmaA8vgGE.HFCtZy.ekH08ky7zJEMvSmygBllvctv0h5fAxCG', NULL, NULL, 'normal', NULL, '2022-06-19 20:49:59', NULL, NULL, '2022-06-19 20:49:59');
INSERT INTO `system_user` VALUES (12, 'yonghu', '$2a$10$KEO5sRKSKe4gcgoqrM7bC.CjhsRmuf5b8AdkcrJZRdOOD/81eGs4S', '132111111', NULL, 'disable', NULL, '2022-06-28 22:32:04', NULL, NULL, '2022-06-28 22:32:04');
COMMIT;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` VALUES (1, 1, 1);
INSERT INTO `system_user_role` VALUES (18, 9, 1);
INSERT INTO `system_user_role` VALUES (23, 4, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
