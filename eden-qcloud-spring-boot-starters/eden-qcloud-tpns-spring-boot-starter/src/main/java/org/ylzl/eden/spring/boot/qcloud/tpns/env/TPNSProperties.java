package org.ylzl.eden.spring.boot.qcloud.tpns.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TPNS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.tpns")
public class TPNSProperties {

	private boolean enabled;

	private String secretId;

	private String secretKey;
}
