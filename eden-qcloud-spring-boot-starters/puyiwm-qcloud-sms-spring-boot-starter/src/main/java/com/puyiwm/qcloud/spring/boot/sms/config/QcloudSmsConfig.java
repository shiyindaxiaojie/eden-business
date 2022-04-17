package com.puyiwm.qcloud.spring.boot.sms.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 腾讯云短信配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public class QcloudSmsConfig {

	private String accessKey;

	private String secretKey;

	private String smsSdkAppId;

	private String region;
}
