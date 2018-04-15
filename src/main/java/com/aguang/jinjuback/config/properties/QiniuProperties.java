package com.aguang.jinjuback.config.properties;

import lombok.Data;

/**
 * 七牛云配置类
 */
@Data
public class QiniuProperties {

	/** key配置 */
	private QiniuKeyProperties key = new QiniuKeyProperties();

	@Data
	public class QiniuKeyProperties {
		private String accessKey;
		private String secretKey;
	}
}
