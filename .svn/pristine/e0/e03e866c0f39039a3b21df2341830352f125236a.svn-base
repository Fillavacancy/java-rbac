create database jrbac;
use jrbac;

-- 登录用户表
DROP TABLE IF EXISTS `jrbac_login_user`;
CREATE TABLE `jrbac_login_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id,uuid32位',
  `username` varchar(64) NOT NULL COMMENT '登录用户名',
  `password` varchar(32) NOT NULL COMMENT '登录密码,生成的password也是32位',
  `nickname` varchar(64) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '用户修改时间,要用程序控制,ubuntu上不能设置为now()',
  `account_status` tinyint(4) DEFAULT '0' COMMENT '账户状态,默认为0,被锁定为1',
  `status` tinyint(4) DEFAULT '0' COMMENT '账户状态,默认为0,超级管理员1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jrbac_login_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录用户表';

-- 初始化数据  密码12345678
INSERT INTO `jrbac_login_user` VALUES ('fa2c960b3d1046f0b82341a0a690ce11', 'chenggaowei', 'e42584918d922300a0498dbb6e89745d', '程同学', '15353530000', 'peer44@qq.com', '2016-10-26 13:57:04', null, '0', '1');

-- 菜单表
DROP TABLE IF EXISTS `jrbac_menu`;
CREATE TABLE `jrbac_menu` (
  `id` varchar(32) NOT NULL COMMENT '主键id,uuid32位',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(64) DEFAULT NULL COMMENT '访问地址',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `order` int(4) DEFAULT '0' COMMENT '菜单顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';


-- 角色表
DROP TABLE IF EXISTS `jrbac_role`;
CREATE TABLE `jrbac_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id,uuid32位',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间，主要是用来进行排序',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 角色菜单表
DROP TABLE IF EXISTS `jrbac_role_menu`;
CREATE TABLE `jrbac_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `jrbac_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `jrbac_menu` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- 用户角色表
DROP TABLE IF EXISTS `jrbac_user_role`;
CREATE TABLE `jrbac_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jrbac_login_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_role_id_user_role` FOREIGN KEY (`role_id`) REFERENCES `jrbac_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';


DROP TABLE IF EXISTS `jrbac_commodity`;
DROP TABLE IF EXISTS `jrbac_brand`;
CREATE TABLE `jrbac_brand` (
  `br_Id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `br_Name` varchar(64) NOT NULL COMMENT '品牌名称',
  PRIMARY KEY (`br_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';

CREATE TABLE `jrbac_commodity` (
  `com_Id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `com_Name` varchar(64) NOT NULL COMMENT '商品名称',
  `com_Title` varchar(64) NOT NULL COMMENT '商品标题',
  `com_Content` varchar(2000) DEFAULT NULL COMMENT '商品内容',
  `com_Price` double NOT NULL COMMENT '商品价格',
  `com_Number` int(4) NOT NULL COMMENT '商品数量',
  `com_Type` varchar(30) DEFAULT NULL COMMENT '商品类别',
  `com_CreateDate` datetime NOT NULL COMMENT '创建时间',
  `com_UpDate` datetime NOT NULL COMMENT '修改时间',
  `com_SalesVolume` int(4) DEFAULT '0' COMMENT '销量',
  `com_Brand` tinyint(4) DEFAULT NULL COMMENT '品牌',
  `com_State` bit(1) DEFAULT 1 COMMENT '状态',
  `com_ImgURL` varchar(300) NOT NULL COMMENT '图片路径',
  PRIMARY KEY (`com_Id`),
  CONSTRAINT `fk_com_Brand` FOREIGN KEY (`com_Brand`) REFERENCES `jrbac_brand` (`br_Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

DROP TABLE IF EXISTS `jrbac_search_log`;
DROP TABLE IF EXISTS `jrbac_search_log`;
CREATE TABLE `jrbac_search_log` (
  `sl_Id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '搜索记录id',
  `sl_Content` varchar(50) NOT NULL COMMENT '搜索记录内容',
  `sl_SearchCount` tinyint(4) DEFAULT '1' COMMENT '被搜索的记录数',
  PRIMARY KEY (`sl_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索记录表';

CREATE TABLE `jrbac_csdnblog` (
  `key` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id` int(11) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` varchar(16) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `view` int(11) unsigned DEFAULT NULL,
  `comments` int(11) unsigned DEFAULT NULL,
  `copyright` int(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='网络爬虫数据表';
