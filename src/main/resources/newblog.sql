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

 Date: 11/10/2019 22:13:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `author` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `classify` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `keywords` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '正文',
  `is_top` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '置顶',
  `create_by_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,1已上传,0待修改',
  `picture` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传图片',
  `views` int(200) NOT NULL DEFAULT 0 COMMENT '访问量',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  INDEX `category`(`classify`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_content
-- ----------------------------
INSERT INTO `article_content` VALUES (1, '张三', '新闻', '安利进驻国际', '安利进驻国际', '安利进驻国际', '安利进驻国际', '1', '2019-10-11 21:24:34', '2019-10-11 19:09:24', '0', '/images/banner1.jpg', 0, 1);
INSERT INTO `article_content` VALUES (2, '张三', '新闻', '分享是学习的姥姥', '学习', '分享是学习的姥姥', '分享是学习的姥姥', '1', '2019-10-11 21:24:38', '2019-10-11 20:54:19', '0', '/images/banner2.jpg', 0, 2);
INSERT INTO `article_content` VALUES (3, '李四', '体育', '勇士比赛失利', 'NBA', '勇士比赛失利', '勇士比赛失利', '1', '2019-10-11 21:29:39', '2019-10-11 21:29:42', '0', '/images/banner3.jpg', 0, 3);
INSERT INTO `article_content` VALUES (4, '李四', '体育', '中国排球又获得冠军', '世界赛', '中国排球又获得冠军', '中国排球又获得冠军', '1', '2019-10-11 21:31:31', '2019-10-11 21:31:34', '0', '/images/banner4.jpg', 0, 4);
INSERT INTO `article_content` VALUES (5, '李四', '体育', '西部赛区火箭队被出名', 'NBA', '西部赛区火箭队被出名', '西部赛区火箭队被出名', '1', '2019-10-11 21:32:33', '2019-10-11 21:32:37', '0', '/images/banner5.jpg', 0, 5);
INSERT INTO `article_content` VALUES (6, '王五', '美食', '四川火锅又辣又美味', '火锅', '四川火锅又辣又美味', '四川火锅又辣又美味', '1', '2019-10-11 21:35:04', '2019-10-11 21:35:08', '0', '/images/banner6.jog', 0, 6);
INSERT INTO `article_content` VALUES (7, '王五', '2', 'Java排行榜第一', 'Java排行榜第一', 'Java排行榜第一', 'Java排行榜第一', '0', '2019-10-11 22:11:48', '2019-10-11 22:10:47', '0', '/upload/picture/banner5.jpg', 0, 0);

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `article_num` int(11) NULL DEFAULT NULL COMMENT '文章数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (1, '张三', '123456', '张三', '15333334555', '1521902412@qq.com', '作者', 12);
INSERT INTO `author` VALUES (2, '李四', '123456', '李四真', '12343234544', '1521902412@qq.com', '管理员', 11);
INSERT INTO `author` VALUES (3, '王五', '123456', '王小五', '124342344343', '4324', '管理员', 11);
INSERT INTO `author` VALUES (4, '士大夫', '234234', '小明', '12233343455', '1521902412', '作者', 23);
INSERT INTO `author` VALUES (5, 'wuli', '432424', '云天', '123456756444', '15289897666@qq.com', '作者', 22);
INSERT INTO `author` VALUES (6, 'shili', '423423', '四号', '13189898887', 'nidf@gmail.com', '作者', 23);
INSERT INTO `author` VALUES (7, '露酒', '123456', '灰色', '13287237566', '881231723@qq.com', '编辑员', 1);
INSERT INTO `author` VALUES (8, 'd爱国', '123456', '保时捷', '17237265675', '8971236706@qq.com', '超级管理员', 10);
INSERT INTO `author` VALUES (9, 's速度', '123456', '暗黑', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (10, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (11, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (12, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (13, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (14, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (15, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (16, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (17, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (18, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (19, 's速度', '123456', '阿三', '12382371678', '12983128361@qq.com', '作者', 1);
INSERT INTO `author` VALUES (20, '13123', '321313', '312331', '1231123', '2312312', '3123123', 0);
INSERT INTO `author` VALUES (21, 'qweewq', '3123113', '123123', '12312313', '31231231', '1323123', 0);
INSERT INTO `author` VALUES (22, '摩斯花生酥', '123456', '阿托萨蒂大', '1689656717', '126378123@qq.com', '管理员', 0);
INSERT INTO `author` VALUES (23, '摩斯', '135126731', '张三', '126317823', '2387261312@qq.com', '编辑者', 0);
INSERT INTO `author` VALUES (24, '魔兽', '12344', '里斯', '1231726312', '12367215367@qq.com', '超级管理员', 0);
INSERT INTO `author` VALUES (25, 'dauh', '12312', 'suh', '123712812', '126378117@qq.com', '编辑者', 0);
INSERT INTO `author` VALUES (26, '章三', '123123zh', 'zhangsan', '123424242', '123123883@qq.com', '用户', 0);
INSERT INTO `author` VALUES (29, 'ahdua', 'gyuasdg', '张三', '1237821376', '12612513176@qq.com', '用户', 0);
INSERT INTO `author` VALUES (38, '阿瑟东', '123456', '小明', '123453463', '123124323@qq.com', '用户', 0);
INSERT INTO `author` VALUES (39, '阿瑟东', '123456', '小明', '123453463', '123124323@qq.com', '用户', 0);
INSERT INTO `author` VALUES (40, '思密达', '12345', '西斯', '121317862315', '12312631@qq.com', '用户', 0);
INSERT INTO `author` VALUES (41, '思密达', '12345', '西斯', '121317862315', '12312631@qq.com', '用户', 0);
INSERT INTO `author` VALUES (42, '123', '112', '123', '123', '12', '312', 0);
INSERT INTO `author` VALUES (43, '123', '12312', '312', '312', '312', '31', 0);
INSERT INTO `author` VALUES (44, '123', '1231', '231', '2312', '3123', '123', 0);
INSERT INTO `author` VALUES (45, '12', '312', '3123', '123', '123123', '123', 0);
INSERT INTO `author` VALUES (46, '12', '312', '3123', '123', '123123', '123', 0);
INSERT INTO `author` VALUES (47, '123', '312', '3123', '1231', '23123', '1231', 0);
INSERT INTO `author` VALUES (48, '12', '212', '12', '1', '212', '12', 0);
INSERT INTO `author` VALUES (49, '12', '3123', '12312', '1231', '23123', '13123', 0);

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `img_url` varchar(110) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(110) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_link` varchar(110) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, '2019-10-07 16:42:40', '/images/banner1.jpg', 'happy life', '#', '...', 1);
INSERT INTO `carousel` VALUES (2, '2019-10-09 23:16:17', '/upload/picture/banner2.jpg', '#', '#', '#', 2);
INSERT INTO `carousel` VALUES (3, '2019-10-07 16:43:21', '/images/banner3.jpg', '..', '#', '..', 3);
INSERT INTO `carousel` VALUES (32, '2019-10-07 16:43:01', '/upload/picture/banner1.jpg', '轮播图', '#', '无', 32);
INSERT INTO `carousel` VALUES (33, '2019-10-07 15:09:27', '/upload/picture/banner5.jpg', '1', '2', '3', 33);
INSERT INTO `carousel` VALUES (56, '2019-10-09 23:36:26', '/upload/picture/gou.png', '1#', '#', '#', 56);
INSERT INTO `carousel` VALUES (57, '2019-10-09 23:36:32', '/upload/picture/banner4.jpg', 'last', '#', '没有', 57);
INSERT INTO `carousel` VALUES (58, '2019-10-09 23:36:32', '/upload/picture/banner8.jpg', 'lat', '1', '1', 58);
INSERT INTO `carousel` VALUES (61, '2019-10-09 23:36:49', '/upload/picture/banner7.jpg', '轮播图', '#', '无', 61);
INSERT INTO `carousel` VALUES (62, '2019-10-07 16:41:11', '/upload/picture/banner7.jpg', '1', '1', '1', 62);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_by_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `parent_directory` varchar(150) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '父目录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '新闻', '最近7天的新闻', '2019-10-11 19:03:25', '2019-10-11 19:03:28', NULL);
INSERT INTO `category` VALUES (2, '美食', '中国内的美食', '2019-10-11 19:04:07', '2019-10-11 19:04:10', NULL);
INSERT INTO `category` VALUES (3, '体育', '爱好运动者', '2019-10-11 19:04:50', '2019-10-11 19:04:48', NULL);

-- ----------------------------
-- Table structure for company_profile
-- ----------------------------
DROP TABLE IF EXISTS `company_profile`;
CREATE TABLE `company_profile`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '正文',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_profile
-- ----------------------------
INSERT INTO `company_profile` VALUES (1, '安利', '走向全球', '2019-10-10 20:16:59', '/upload/picture/banner1.jpg', '安利是中国最大的品牌', 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `operate_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址',
  `operate_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_view
-- ----------------------------
DROP TABLE IF EXISTS `sys_view`;
CREATE TABLE `sys_view`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问ip',
  `create_by_date` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `number` int(11) NULL DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
