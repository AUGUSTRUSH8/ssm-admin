use ssm_demo;
DROP TABLE IF EXISTS `tb_class_four`;
CREATE TABLE `tb_class_four` (
`id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`description` varchar(100) NOT NULL DEFAULT '''''' COMMENT '描述',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_class_four` WRITE;
/*!40000 ALTER TABLE `tb_class_four` DISABLE KEYS */;

INSERT INTO `tb_class_four` (`id`, `description`, `create_time`)
VALUES
	(1,'自己动手实现一个精美且实用的JavaWeb后台管理系统 达人课','2018-06-27 15:12:13');

/*!40000 ALTER TABLE `tb_class_four` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `tb_admin_user`;

CREATE TABLE `tb_admin_user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(20) NOT NULL DEFAULT '''''' COMMENT '用户名',
  `password_md5` varchar(50) NOT NULL DEFAULT '''''' COMMENT '密码',
  `user_token` varchar(50) NOT NULL DEFAULT '''''' COMMENT 'token值',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `tb_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_admin_user` DISABLE KEYS */;

INSERT INTO `tb_admin_user` (`id`, `user_name`, `password_md5`, `user_token`, `is_deleted`, `create_time`)
VALUES
	(1,'admin','e10adc3949ba59abbe56e057f20f883e','6f1d93269e8bfdcd2066a248bfdafee6',0,'2018-07-04 11:21:14');

/*!40000 ALTER TABLE `tb_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `tb_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_admin_user` DISABLE KEYS */;

INSERT INTO `tb_admin_user` (`id`, `user_name`, `password_md5`, `user_token`, `is_deleted`, `create_time`)
VALUES
	(3,'小明','e10adc3949ba59abbe56e057f20f883e','6f1d93269e8bfdcd2066a248bfdafee6',0,'2018-07-05 11:21:14');

/*!40000 ALTER TABLE `tb_admin_user` ENABLE KEYS */;
UNLOCK TABLES;



CREATE TABLE `tb_ssm_picture` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `path` varchar(50) NOT NULL DEFAULT '' COMMENT '图片路径',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已删除 0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `tb_ssm_article` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_title` varchar(200) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标题',
  `article_content` text COLLATE utf8_bin NOT NULL COMMENT '内容',
  `add_name` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '添加人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;