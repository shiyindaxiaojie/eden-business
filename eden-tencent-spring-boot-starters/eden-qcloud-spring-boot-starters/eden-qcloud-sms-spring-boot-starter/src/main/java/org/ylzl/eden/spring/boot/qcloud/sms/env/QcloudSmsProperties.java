package org.ylzl.eden.spring.boot.qcloud.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.ylzl.eden.spring.boot.qcloud.sms.config.QcloudSmsConfig;

/**
 * 腾讯云短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = QcloudSmsProperties.PREFIX)
public class QcloudSmsProperties {

	public static final String PREFIX = "tencent.cloud.sms";

	public static final String ENABLED = PREFIX + ".enabled";

	private boolean enabled;

	private QcloudSmsConfig sms;
}
