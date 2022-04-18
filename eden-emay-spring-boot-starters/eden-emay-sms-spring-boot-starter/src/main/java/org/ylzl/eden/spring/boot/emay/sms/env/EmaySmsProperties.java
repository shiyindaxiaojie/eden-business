package org.ylzl.eden.spring.boot.emay.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 亿美短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "emay.sms")
public class EmaySmsProperties {

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 密钥
	 */
	private String secretKey;

	/**
	 * 接口地址
	 */
	private String host;
}
