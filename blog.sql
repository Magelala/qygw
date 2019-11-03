/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 03/11/2019 16:31:38
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
  `classify` int(11) NULL DEFAULT NULL COMMENT '分类',
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
  INDEX `category`(`classify`) USING BTREE,
  CONSTRAINT `article_content_ibfk_1` FOREIGN KEY (`classify`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_content
-- ----------------------------
INSERT INTO `article_content` VALUES (18, 'sadasdsa', 2, 'asdashjodiasjodasd', 'asdasdsa', 'asdasdasdas', '', '0', '2019-10-17 19:54:09', '2019-10-17 19:54:09', NULL, '/upload/picture/追风的女儿.jpg', 0, 0);
INSERT INTO `article_content` VALUES (19, 'sad撒大所', 1, '撒大声地', '萨达所大所', '萨达所大所', 'sad撒撒多', '0', '2019-10-17 20:27:11', '2019-10-17 20:27:11', NULL, '/upload/picture/追风的女儿.jpg', 0, 0);
INSERT INTO `article_content` VALUES (20, 'sadddas', 1, 'hahahaha', 'asdfghjk', 'sadasdfgggh', 'zhehisadasdas', '0', '2019-10-25 20:30:06', '2019-10-25 20:30:06', NULL, '/upload/picture/牧羊曲.jpg', 0, 0);
INSERT INTO `article_content` VALUES (21, 'asdsadas', 2, 'meishi', 'sadasdsadas', 'asdsada', 'asdasdsa', '0', '2019-10-25 20:30:47', '2019-10-25 20:30:47', NULL, '/upload/picture/追风的女儿.jpg', 0, 0);
INSERT INTO `article_content` VALUES (22, '萨达所大所', 3, 'iooi', '奥术大师大所多', '撒调查', '你卡梅隆', '0', '2019-10-27 16:01:31', '2019-10-27 16:01:31', NULL, '/upload/picture/追风的女儿.jpg', 0, 0);
INSERT INTO `article_content` VALUES (23, '你哦sad', 2, '哈哈哈哈沙雕', '撒大声地', '你测试', '美食大师大扫荡', '0', '2019-10-30 19:46:05', '2019-10-30 19:46:05', NULL, '/upload/picture/u=3097118575,3800333031&fm=26&gp=0.jpg', 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, '2019-10-21 20:01:40', '/images/banner1.jpg', 'happy lifeaaa', '#', '...', 3);
INSERT INTO `carousel` VALUES (3, '2019-10-28 19:46:39', '/images/banner3.jpg', '..哈哈哈123', '#', '..', 1);
INSERT INTO `carousel` VALUES (33, '2019-10-07 15:09:27', '/upload/picture/banner5.jpg', '1', '2', '3', 33);
INSERT INTO `carousel` VALUES (56, '2019-10-09 23:36:26', '/upload/picture/gou.png', '1#', '#', '#', 56);
INSERT INTO `carousel` VALUES (57, '2019-10-09 23:36:32', '/upload/picture/banner4.jpg', 'last', '#', '没有', 57);
INSERT INTO `carousel` VALUES (58, '2019-10-09 23:36:32', '/upload/picture/banner8.jpg', 'lat', '1', '1', 58);
INSERT INTO `carousel` VALUES (61, '2019-10-09 23:36:49', '/upload/picture/banner7.jpg', '轮播图', '#', '无', 61);
INSERT INTO `carousel` VALUES (62, '2019-10-07 16:41:11', '/upload/picture/banner7.jpg', '1', '1', '1', 62);
INSERT INTO `carousel` VALUES (68, '2019-10-16 09:41:59', '/upload/picture/牧羊曲.jpg', 'asdsad', 'sadsada', 'saasdsad', 63);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '新闻', '最近7天的新闻', '2019-10-11 19:03:25', '2019-10-11 19:03:28', NULL);
INSERT INTO `category` VALUES (2, '美食', '中国内的美食', '2019-10-11 19:04:07', '2019-10-11 19:04:10', NULL);
INSERT INTO `category` VALUES (3, '体育', '爱好运动者', '2019-10-11 19:04:50', '2019-10-11 19:04:48', NULL);
INSERT INTO `category` VALUES (4, '考研', '考研党', NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_profile
-- ----------------------------
INSERT INTO `company_profile` VALUES (1, '安利', '走向全球', '2019-10-10 20:16:59', '/upload/picture/banner1.jpg', '安利是中国最大的品牌', 1);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('张三', '+jSbnZ3gB1Kn3QALr7/1tA==', 'WdZNbX6zl6cJjpCBbVWJ5g==', '2019-10-30 20:47:27');
INSERT INTO `persistent_logins` VALUES ('张三', '05Lq7Epyfz+htp24/li3yQ==', 'DE1QV3Dta6HB4lsOVaIleQ==', '2019-10-24 16:46:17');
INSERT INTO `persistent_logins` VALUES ('张三', '2HHsKYzk//uwoMuRJiLNyw==', 'qklbqQ0NToG9VWX1kv/aaw==', '2019-10-28 19:49:17');
INSERT INTO `persistent_logins` VALUES ('张三', '3Mm0VP+iiKblHu511y7SkQ==', 'z6cowLoGcRy5JlqtYIRqdA==', '2019-10-30 20:40:00');
INSERT INTO `persistent_logins` VALUES ('张三', '3SYJvj7xAhJrqpD2ZAUyyw==', 'aR0K4bz13n9z8L9ch+YRhA==', '2019-10-27 11:22:15');
INSERT INTO `persistent_logins` VALUES ('张三', '48blOok+whyiudL0lyTmLg==', 'EFpl6uXOsAkRhnsUpTTU+w==', '2019-10-25 20:29:35');
INSERT INTO `persistent_logins` VALUES ('张三', '59nRbJyN34EOt/p5LaoVzw==', '6CBzDvyp/lODmNBifOwHjg==', '2019-10-27 15:47:36');
INSERT INTO `persistent_logins` VALUES ('张三', '7b9gcv8e46Pg/eILakZxMQ==', 'FVkMzqI4EyQEw40UyW3fhA==', '2019-10-23 21:21:47');
INSERT INTO `persistent_logins` VALUES ('张三', '9oru8Of3+Fq2Y7cH+woZZw==', 'JvGIim+DX66FEXf6BTz1RQ==', '2019-11-01 06:57:19');
INSERT INTO `persistent_logins` VALUES ('张三', 'EQuLL+LMsQxKO3q5XSnGXQ==', 'PNwy/E4fiddI+vRCCJxGgg==', '2019-10-30 20:46:09');
INSERT INTO `persistent_logins` VALUES ('张三', 'JsWusPdmW++q7VtrUuv98w==', 'CUGgspCo6qksuHSGeRJt3g==', '2019-10-29 20:52:06');
INSERT INTO `persistent_logins` VALUES ('张三', 'LKrQkGbakBF5312bY8+W2A==', 'BFXkwweGDrQocAduJl0xHA==', '2019-10-27 15:59:43');
INSERT INTO `persistent_logins` VALUES ('张三', 'LT58zg7z+hKQhM1leoS0BA==', 'IdCVOcl4utmivDg2CyvofQ==', '2019-10-28 19:45:05');
INSERT INTO `persistent_logins` VALUES ('张三', 'MEVvYSjLd00THadaG2Ux1g==', 'ywBkQk0ZRlF/Mtq1qRJu5g==', '2019-10-26 20:13:48');
INSERT INTO `persistent_logins` VALUES ('张三', 'O+ld09dIjDNdQSJBI07q0w==', 'IMhBiZ6yAJuxSYBX9iqdxA==', '2019-10-29 20:56:01');
INSERT INTO `persistent_logins` VALUES ('张三', 'OBhsErxE0UflGeDcxAiH3A==', '7sDW/WPYE8lIxreqa/sVGQ==', '2019-10-27 15:51:11');
INSERT INTO `persistent_logins` VALUES ('张三', 'OK1nDa2GwxD4Mz7QxAlONg==', 'z+MVovAcWvAsgqY1JUoB7A==', '2019-10-29 20:35:58');
INSERT INTO `persistent_logins` VALUES ('张三', 'PQM1KTW1jaWLplj7HHkldQ==', '13sDIz676X0RsYYUmYUQPQ==', '2019-10-29 20:50:41');
INSERT INTO `persistent_logins` VALUES ('张三', 'RngPTZVDBvHPps/0lMycag==', '3ye5rSAP1gEA7t0k7OiV3Q==', '2019-10-30 20:10:51');
INSERT INTO `persistent_logins` VALUES ('张三', 'SzJYsXb+G5DLOZ7P3JwHKQ==', 'NNdLFNLBMFzcBl9zhoQjeA==', '2019-10-30 21:38:14');
INSERT INTO `persistent_logins` VALUES ('张三', 'WircA5yyJbXwWsj2hhhZWw==', '0rIA7NzCJ+r5To7XVgdWCw==', '2019-10-21 21:13:32');
INSERT INTO `persistent_logins` VALUES ('张三', 'XXLSfEIU803/3P4yugoEAQ==', 'uytV4Kr/MvtDlz9jc5uJUg==', '2019-10-23 21:23:42');
INSERT INTO `persistent_logins` VALUES ('张三', 'b/bkFReNr1ZC+uFScbWC3Q==', 'mwSbdj4ub+yT/v+PIVwhBQ==', '2019-10-29 20:43:28');
INSERT INTO `persistent_logins` VALUES ('张三', 'c/g3b3KEExJt/arAxt5FUg==', 'dAEUwkTQqTPcl6Wjix/rzw==', '2019-11-03 15:39:17');
INSERT INTO `persistent_logins` VALUES ('张三', 'c4fQ+z5AbIKdMyQuf2pusg==', 'JfUfx3HsQUayaUunj1d9fw==', '2019-10-29 20:19:35');
INSERT INTO `persistent_logins` VALUES ('张三', 'ehgg3FM/rwUzROxQjco5Wg==', 'J7cswNHXj8CJn05kwUUXKQ==', '2019-10-21 20:51:27');
INSERT INTO `persistent_logins` VALUES ('张三', 'icli0OpGJGIBeemy+pQwCw==', 'TpfFT66B/Vji5ZDV4ZxOqA==', '2019-10-29 20:58:35');
INSERT INTO `persistent_logins` VALUES ('张三', 'l+uwMAJCu+bymJqvmiR2+w==', '45EDQ7NCapMiW64kamduIA==', '2019-10-27 11:22:31');
INSERT INTO `persistent_logins` VALUES ('张三', 'nY+lIHzfhFtlVHpYc72WCg==', 'mfr3ociL9oR7OEJFwESMNQ==', '2019-10-21 20:59:51');
INSERT INTO `persistent_logins` VALUES ('张三', 'nZcOGp4AvRdncIXXTULEEw==', 'Nx5kn9xEqMe4VrBsEYf+GQ==', '2019-10-21 20:59:32');
INSERT INTO `persistent_logins` VALUES ('张三', 'oXpQrCQnaRg9KsLnca6edA==', 'ifHUR/y5NRKy+3PdKZQQSw==', '2019-10-21 21:08:12');
INSERT INTO `persistent_logins` VALUES ('张三', 'oj+iRc1CupXiVwOM2F/Ykg==', 'zyV4DcEVyuXccGCftUs5PA==', '2019-10-23 20:22:57');
INSERT INTO `persistent_logins` VALUES ('张三', 'qfs3QFRxbc12FO7q2ymhMQ==', 'xvGTQHMY8KGurqYAn0sByQ==', '2019-10-28 19:39:54');
INSERT INTO `persistent_logins` VALUES ('张三', 'xGCbYSwtK4ye5CDVtTSzvw==', 'pSrmWMIES1eDNuwYru9RJw==', '2019-10-28 20:50:27');

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
