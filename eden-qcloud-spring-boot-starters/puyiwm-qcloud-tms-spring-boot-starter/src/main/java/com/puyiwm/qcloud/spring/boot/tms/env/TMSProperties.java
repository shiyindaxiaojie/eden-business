package com.puyiwm.qcloud.spring.boot.tms.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TMS 配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "qcloud.tms")
public class TMSProperties {

	private Boolean enabled;

	private String secretId;

	private String secretKey;
}
