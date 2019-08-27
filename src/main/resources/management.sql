/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : management

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 23/08/2019 22:50:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad_picture
-- ----------------------------
DROP TABLE IF EXISTS `ad_picture`;
CREATE TABLE `ad_picture`  (
  `id` int(11) NOT NULL,
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片链接',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(11) NOT NULL,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `comment_id` int(11) DEFAULT NULL COMMENT '对应的留言ID',
  `create_by_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_comment
-- ----------------------------
INSERT INTO `article_comment` VALUES (1, 1, 2, '2019-08-15 08:17:20');
INSERT INTO `article_comment` VALUES (2, 1, 2, '2019-08-16 08:50:17');
INSERT INTO `article_comment` VALUES (4, 2, 4, '2019-08-21 08:20:18');
INSERT INTO `article_comment` VALUES (5, 2, 2, '2019-08-27 08:17:43');
INSERT INTO `article_comment` VALUES (6, 1, 2, '2019-08-21 08:21:56');
INSERT INTO `article_comment` VALUES (7, 1, 4, '2019-08-28 08:22:00');
INSERT INTO `article_comment` VALUES (8, 1, 45, '2019-08-27 08:22:03');
INSERT INTO `article_comment` VALUES (9, 1, 11, '2019-08-20 08:22:07');

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `id` int(11) NOT NULL,
  `article_id` int(11) DEFAULT NULL COMMENT '对应文章ID',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章内容//；路径存取',
  `create_by_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_top` int(255) DEFAULT NULL COMMENT '是否置顶',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id1`(`article_id`) USING BTREE,
  CONSTRAINT `article_id1` FOREIGN KEY (`article_id`) REFERENCES `article_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_content
-- ----------------------------
INSERT INTO `article_content` VALUES (1, 1, '程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员程序员', '2019-08-06 08:52:07', '2019-08-20 08:52:11', 1);
INSERT INTO `article_content` VALUES (2, 2, NULL, '2019-08-22 08:52:31', '2019-08-29 08:52:35', 0);
INSERT INTO `article_content` VALUES (3, 1, NULL, '2019-08-15 08:52:53', '2019-08-20 08:52:57', 1);
INSERT INTO `article_content` VALUES (4, 1, NULL, '2019-08-14 08:53:17', '2019-08-22 08:53:20', 1);
INSERT INTO `article_content` VALUES (5, 1, NULL, '2019-08-30 08:53:54', '2019-08-28 08:53:58', 0);

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '正文',
  `traffic` int(255) DEFAULT NULL COMMENT '文章访问量',
  `create_by_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES (1, '新闻1', '正文', 999, '2019-08-06 23:17:25', '2019-08-30 23:17:29');
INSERT INTO `article_info` VALUES (2, '新闻2', '正文', 233, '2019-08-16 23:18:18', '2019-08-23 23:18:22');
INSERT INTO `article_info` VALUES (3, '新闻3', '正文', 0, '2019-08-28 08:55:48', '2019-08-30 08:55:51');

-- ----------------------------
-- Table structure for article_picture
-- ----------------------------
DROP TABLE IF EXISTS `article_picture`;
CREATE TABLE `article_picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `author_id` int(11) DEFAULT NULL COMMENT '作者ID',
  `picture_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片//使用路径',
  `create_by_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_by_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id2`(`article_id`) USING BTREE,
  INDEX `author_id`(`author_id`) USING BTREE,
  CONSTRAINT `article_id2` FOREIGN KEY (`article_id`) REFERENCES `article_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_picture
-- ----------------------------
INSERT INTO `article_picture` VALUES (1, 2, 1, NULL, '2019-08-08 23:18:54', '2019-08-30 23:18:58');
INSERT INTO `article_picture` VALUES (2, 1, 4, NULL, '2019-08-29 08:56:55', '2019-08-18 08:56:59');
INSERT INTO `article_picture` VALUES (3, 3, 4, NULL, '2019-08-07 08:57:10', '2019-08-28 08:57:13');
INSERT INTO `article_picture` VALUES (4, 1, 5, NULL, '2019-08-22 08:57:32', '2019-08-27 08:57:35');

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色',
  `article_num` int(11) DEFAULT NULL COMMENT '文章数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (1, '张三', '123456', '张三', '15333334555', '1521902412@qq.com', '作者', 12);
INSERT INTO `author` VALUES (2, '李四', '123456', '李四真', '12343234544', '1521902412@qq.com', '管理员', 11);
INSERT INTO `author` VALUES (3, '王五', '123456', '王小五', '124342344343', '4324', '管理员', 11);
INSERT INTO `author` VALUES (4, '士大夫', '234234', '小明', '12233343455', '1521902412', '作者', 23);
INSERT INTO `author` VALUES (5, 'wuli', '432424', '云天', '123456756444', '15289897666@qq.com', '作者', 22);
INSERT INTO `author` VALUES (6, 'shili', '423423', '四号', '13189898887', 'nidf@gmail.com', '作者', 23);

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `create_by_date` datetime DEFAULT NULL,
  `modified_by_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id3`(`article_id`) USING BTREE,
  CONSTRAINT `article_id3` FOREIGN KEY (`article_id`) REFERENCES `article_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES (1, 1, 1, '2019-08-22 08:58:15', '2019-08-30 08:58:19');
INSERT INTO `category_info` VALUES (2, 2, 3, '2019-08-22 08:58:41', '2019-08-21 08:58:44');
INSERT INTO `category_info` VALUES (3, 3, 1, '2019-08-28 08:58:57', '2019-08-28 08:59:00');
INSERT INTO `category_info` VALUES (4, 4, 2, '2019-08-29 08:59:15', '2019-08-20 08:59:19');
INSERT INTO `category_info` VALUES (5, 5, 3, '2019-08-27 08:59:32', '2019-08-05 08:59:35');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作内容',
  `operate_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `operate_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_view
-- ----------------------------
DROP TABLE IF EXISTS `sys_view`;
CREATE TABLE `sys_view`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问ip',
  `create_by_date` datetime DEFAULT NULL COMMENT '访问时间',
  `number` int(11) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
