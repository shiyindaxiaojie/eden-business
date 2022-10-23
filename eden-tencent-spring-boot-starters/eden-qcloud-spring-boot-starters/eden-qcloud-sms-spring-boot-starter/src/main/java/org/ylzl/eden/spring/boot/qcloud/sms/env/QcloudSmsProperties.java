package org.ylzl.eden.spring.boot.qcloud.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.sms")
public class QcloudSmsProperties {

	private boolean enabled;

	private String accessKey;

	private String secretKey;

	private String smsSdkAppId;

	private String region;
}
