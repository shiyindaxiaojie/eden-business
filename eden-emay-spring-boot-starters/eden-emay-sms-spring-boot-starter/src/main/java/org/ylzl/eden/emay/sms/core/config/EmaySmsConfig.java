package org.ylzl.eden.emay.sms.core.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 亿美短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public class EmaySmsConfig {

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
