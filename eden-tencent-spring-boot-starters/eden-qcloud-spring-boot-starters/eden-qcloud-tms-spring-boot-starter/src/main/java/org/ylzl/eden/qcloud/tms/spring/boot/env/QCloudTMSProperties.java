package org.ylzl.eden.qcloud.tms.spring.boot.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TMS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.tms")
public class QCloudTMSProperties {

	private boolean enabled;

	private String secretId;

	private String secretKey;
}
