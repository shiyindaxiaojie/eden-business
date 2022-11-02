package org.ylzl.eden.qcloud.cmq.spring.boot.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CMQ 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.cmq")
public class QCloudCMQProperties {

	private boolean enabled;

	private String secretId;

	private String secretKey;
}
