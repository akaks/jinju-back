CREATE TABLE `jj_user`(
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `tel` bigint(19) NOT NULL COMMENT '手机号=用户名',
  `password` varchar(35) NOT NULL COMMENT '密码',
  `nickname` VARCHAR (30) DEFAULT NULL COMMENT '昵称',
  `secret` VARCHAR (35) NOT NULL COMMENT '密钥',
  `portrait` VARCHAR (120) DEFAULT NULL COMMENT '头像地址',
  `i_card` VARCHAR (18) NOT NULL DEFAULT '' COMMENT '身份证',
  `area` INT (10) NOT NULL DEFAULT '0' COMMENT '所在区域',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 333 DEFAULT CHARSET = utf8;