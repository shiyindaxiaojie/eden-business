package com.puyiwm.qcloud.spring.boot.vod.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云VOD 配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2021-06-17
 **/
@Data
@ConfigurationProperties(prefix = "qcloud.vod")
public class VODProperties {

	private Boolean enabled;

	private String secretId;

	private String secretKey;

	private String region;

	private Integer shortignValidDuration;
}