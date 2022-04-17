package com.puyiwm.qcloud.spring.boot.sms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云短信配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.sms")
public class QcloudSmsProperties {

	private Boolean enabled;

	private String accessKey;

	private String secretKey;

	private String smsSdkAppId;

	private String region;
}