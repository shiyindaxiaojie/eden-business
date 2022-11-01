package org.ylzl.eden.spring.boot.emay.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 亿美短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = EmaySmsProperties.PREFIX)
public class EmaySmsProperties {

	public static final String PREFIX = "emay.sms";

	public static final String ENABLED = PREFIX + ".enabled";

	private boolean enabled;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 密钥
	 */
	private String secretKey;

	/**
	 * 地址
	 */
	private String ip;

	/**
	 * 端口
	 */
	private int port;
}
