package org.ylzl.eden.spring.boot.qcloud.sms.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 腾讯云短信配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
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
