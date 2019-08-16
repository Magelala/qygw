/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 16/08/2019 12:19:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for articlecomment
-- ----------------------------
DROP TABLE IF EXISTS `articlecomment`;
CREATE TABLE `articlecomment`  (
  `id` int(11) NOT NULL,
  `articleId` int(11) NULL DEFAULT NULL,
  `commentId` int(11) NULL DEFAULT NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_comment`(`articleId`) USING BTREE,
  CONSTRAINT `article_comment` FOREIGN KEY (`articleId`) REFERENCES `articleinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for articlecontent
-- ----------------------------
DROP TABLE IF EXISTS `articlecontent`;
CREATE TABLE `articlecontent`  (
  `id` int(11) NOT NULL,
  `context` mediumblob NULL,
  `articleId` int(11) NULL DEFAULT NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  `modifiedByDate` datetime NULL DEFAULT NULL,
  `isTop` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_content1`(`articleId`) USING BTREE,
  CONSTRAINT `article_content1` FOREIGN KEY (`articleId`) REFERENCES `articleinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for articleinfo
-- ----------------------------
DROP TABLE IF EXISTS `articleinfo`;
CREATE TABLE `articleinfo`  (
  `id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `summary` mediumblob NULL,
  `traffic` int(255) NULL DEFAULT NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  `modifiedByDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for articlepicture
-- ----------------------------
DROP TABLE IF EXISTS `articlepicture`;
CREATE TABLE `articlepicture`  (
  `id` int(11) NOT NULL,
  `articleId` int(11) NULL DEFAULT NULL,
  `authorId` int(11) NULL DEFAULT NULL,
  `pictureBy` mediumblob NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  `modifiedByDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_picture1`(`articleId`) USING BTREE,
  INDEX `article_picture2`(`authorId`) USING BTREE,
  CONSTRAINT `article_picture1` FOREIGN KEY (`articleId`) REFERENCES `articleinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_picture2` FOREIGN KEY (`authorId`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `articleNum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for categoryinfo
-- ----------------------------
DROP TABLE IF EXISTS `categoryinfo`;
CREATE TABLE `categoryinfo`  (
  `id` int(11) NOT NULL,
  `categoryId` int(11) NULL DEFAULT NULL,
  `articleId` int(11) NULL DEFAULT NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  `modifiedByDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog`  (
  `id` int(11) NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `operateUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `operateBy` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sysview
-- ----------------------------
DROP TABLE IF EXISTS `sysview`;
CREATE TABLE `sysview`  (
  `id` int(11) NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `createByDate` datetime NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
