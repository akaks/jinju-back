package com.aguang.jinjuback.config;

import com.aguang.jinjuback.config.properties.JinjuProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 金句猫配置加载
 */
@Configuration
@EnableConfigurationProperties(JinjuProperties.class)
public class JinjuCoreConfig {
}
