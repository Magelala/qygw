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

 Date: 14/10/2019 22:05:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nav
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '导航栏id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级导航的id,0表示第一级导航',
  `url` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导航链接',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '当前时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_show` tinyint(1) NOT NULL DEFAULT 1 COMMENT '布尔类型,0隐藏,1显示,默认设置了显示',
  `description` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nav
-- ----------------------------
INSERT INTO `nav` VALUES (1, '走进安利', 0, '#', '2019-10-14 21:00:06', '2019-10-14 21:00:06', 1, '第一级菜单', 1);
INSERT INTO `nav` VALUES (2, '产品展式', 0, '#', '2019-10-14 21:00:08', '2019-10-14 21:00:08', 1, '第一级菜单', 2);
INSERT INTO `nav` VALUES (3, '公司简介', 1, 'www.haha.com/1', '2019-10-14 21:00:10', '2019-10-14 21:00:10', 1, '第二级菜单', 1);
INSERT INTO `nav` VALUES (4, '安利营销', 1, 'www.haha.com/2', '2019-10-14 21:00:11', '2019-10-14 21:00:11', 1, '第二级菜单', 2);
INSERT INTO `nav` VALUES (5, '地方之窗', 2, 'www.haha.com/3', '2019-10-14 21:00:12', '2019-10-14 21:00:12', 1, '第二级菜单', 1);
INSERT INTO `nav` VALUES (6, '员工招聘', 2, ' www.haha.com/4', '2019-10-14 21:00:16', '2019-10-14 21:00:16', 1, '第二级菜单', 2);

SET FOREIGN_KEY_CHECKS = 1;
