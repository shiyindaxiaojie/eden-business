package com.puyiwm.qcloud.spring.boot.cmq.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CMQ 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.cmq")
public class CMQProperties {

	private Boolean enabled;

	private String secretId;

	private String secretKey;
}
