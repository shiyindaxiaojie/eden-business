package org.ylzl.eden.qcloud.tpns.spring.boot.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TPNS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.tpns")
public class QCloudTPNSProperties {

	private boolean enabled;

	private String secretId;

	private String secretKey;
}
