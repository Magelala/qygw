/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/11/2019 16:21:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for foot_nav
-- ----------------------------
DROP TABLE IF EXISTS `foot_nav`;
CREATE TABLE `foot_nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '底部导航名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '底部导航链接',
  `remark` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foot_nav
-- ----------------------------
INSERT INTO `foot_nav` VALUES (1, '安利公益基金会', 'www.haha.com', '无');
INSERT INTO `foot_nav` VALUES (2, '安利全球官方网站', 'http://www.haha.com', '新增1');
INSERT INTO `foot_nav` VALUES (5, '阿波罗', 'http://www.haha.com', '2');

SET FOREIGN_KEY_CHECKS = 1;
