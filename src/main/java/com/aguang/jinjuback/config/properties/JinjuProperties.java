package com.aguang.jinjuback.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 金句猫配置类
 */
@Data
@ConfigurationProperties(prefix = "jinju")
public class JinjuProperties {

	private QiniuProperties qiniu = new QiniuProperties();
}
