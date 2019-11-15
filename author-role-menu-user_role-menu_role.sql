
/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '123456',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  `article_num` int(11) DEFAULT NULL COMMENT '文章数量',
  `picture` varchar(150) DEFAULT NULL COMMENT '上传头像',
  `context` text COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `author` */

insert  into `author`(`id`,`user_name`,`password`,`name`,`tel`,`email`,`role`,`article_num`,`picture`,`context`) values
(1,'gege','$2a$10$zyb.cqAJeDGTGKXCFMvs2ulSHGgnl1xVS0ZkSTUqCKe.nMwgBMy/G','gege','12','1521902412@qq.com','2',1,'',''),
(2,'李四','123456','李四真','12343234544','1521902412@qq.com','编辑员',11,'',''),
(4,'士大夫','234234','小明','12233343455','1521902412','1',23,'',''),
(5,'wuli','123456','丽丽','12345678902','3108136635@qq.com','1',22,'','KKKKKKKKKKKKKKKKKKKKKK看看'),
(6,'shili','423423','四号','13189898887','nidf@gmail.com','作者',23,'',''),




/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `icon_cls` varchar(64) DEFAULT NULL,
  `keep_alive` tinyint(1) DEFAULT NULL,
  `require_auth` tinyint(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`url`,`path`,`component`,`name`,`icon_cls`,`keep_alive`,`require_auth`,`parent_id`,`enabled`) values
(1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),
(2,'/main/**','/home','Home','员工资料','fa fa-windows',NULL,1,1,1),
(3,'/article/**','/art/','Home','文章管理','fa fa-windows',NULL,1,1,1),
(4,'/advertise/**','/adv/','Home','广告管理','fa fa-windows',NULL,1,1,1),
(5,'/companyProfile/**','/com/','Home','广告管理-企业简介','fa fa-windows',NULL,1,1,1),
(6,'/nav/**','/nav/','Home','页面管理-导航栏管理','fa fa-windows',NULL,1,1,1),
(7,'/user/**','/user/','Home','用户管理','fa fa-windows',NULL,1,1,1),
(8,'/role/**','/role/','Home','角色管理','fa fa-windows',NULL,1,1,1),
(9,'/menu/**','/menu/','Home','权限管理','fa fa-windows',NULL,1,1,1),
(10,'/setting/**','/set/','Home','设置管理','fa fa-windows',NULL,1,1,1),
(11,'/foot/**','/foot/','Home','页面管理-底部信息管理','fa fa-windows',NULL,1,1,1),
(12,'/logo/**','/logo/','Home','页面管理-公司logo管理','fa fa-windows',NULL,1,1,1);


DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`role_name`,`name`) values
(1,'ROLE_superAdmin','超级管理员'),
(2,'ROLE_author','编辑员');



/*Table structure for table `menu_role` */

DROP TABLE IF EXISTS `menu_role`;

CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=368 DEFAULT CHARSET=utf8;

/*Data for the table `menu_role` */

insert  into `menu_role`(`id`,`mid`,`rid`) values
(347,6,2),
(348,11,2),
(349,12,2),
(359,3,1),
(360,4,1),
(361,5,1),
(362,7,1),
(363,8,1),
(364,9,1),
(365,6,1),
(366,11,1),
(367,12,1);




/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `urid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `user_role_ibfk_1` (`urid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`urid`) REFERENCES `author` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`urid`,`rid`) values
(6,2,2),
(8,4,2),
(67,2,1),
(68,4,1),
(71,5,1),
(74,1,1),
(75,1,2),
(84,65,2),
(85,65,1),
(86,66,2),
(87,66,1);