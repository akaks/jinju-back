CREATE TABLE `jj_user`(
  `user_id` INT (10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR (35) NOT NULL COMMENT '用户名',
  `password` VARCHAR(35) NOT NULL COMMENT '密码',
  `tel` VARCHAR (25) COMMENT '手机号',
  `email` VARCHAR (35) COMMENT '邮箱',
  `photoUrl` VARCHAR (150) COMMENT '头像',
  `address` VARCHAR (150) COMMENT '地址',
  `create_time` bigint(20) COMMENT '创建时间',
  `update_time` bigint(20) COMMENT '修改时间',
  PRIMARY KEY(`user_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 333 DEFAULT CHARSET = utf8;