CREATE TABLE jinju(
  `jinju_id` INT (10) NOT NULL AUTO_INCREMENT COMMENT '金句ID',
  `user_id` INT (10) NOT NULL COMMENT '发表用户ID',
  `content` VARCHAR (1000) NOT NULL COMMENT '金句内容',
  `type` INT (5) NOT NULL COMMENT '类型',
  `create_time` bigint(20) COMMENT '创建时间',
  PRIMARY KEY (`jinju_id`),
  FOREIGN KEY(`user_id`) REFERENCES `jj_user` (`user_id`)
) ENGINE = InnoDB CHARSET = utf8;