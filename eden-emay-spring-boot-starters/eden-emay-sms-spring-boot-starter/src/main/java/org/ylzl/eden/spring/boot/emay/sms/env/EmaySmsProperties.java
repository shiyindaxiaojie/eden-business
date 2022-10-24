package org.ylzl.eden.spring.boot.emay.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.ylzl.eden.spring.boot.emay.sms.config.EmaySmsConfig;

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

	private EmaySmsConfig sms;
}
