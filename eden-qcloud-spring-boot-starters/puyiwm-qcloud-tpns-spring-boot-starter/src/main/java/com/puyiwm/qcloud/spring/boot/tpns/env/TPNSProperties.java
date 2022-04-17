package com.puyiwm.qcloud.spring.boot.tpns.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TPNS 配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "qcloud.tpns")
public class TPNSProperties {

	private Boolean enabled;

	private String secretId;

	private String secretKey;
}
