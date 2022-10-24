package org.ylzl.eden.spring.boot.montnets.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.ylzl.eden.spring.boot.montnets.sms.config.MontnetsSmsConfig;

/**
 * 梦网短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = MontnetsSmsProperties.PREFIX)
public class MontnetsSmsProperties {

	public static final String PREFIX = "montnets.sms";

	public static final String ENABLED = PREFIX + ".enabled";

	private boolean enabled;

	private MontnetsSmsConfig sms;
}
