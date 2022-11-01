package org.ylzl.eden.spring.boot.qcloud.tms.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.tms.env.QcloudTMSProperties;

/**
 * 腾讯云TMS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.tms.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QcloudTMSProperties.class)
@Slf4j
@Configuration
public class QcloudTMSAutoCofiguration {

	private final QcloudTMSProperties qcloudTmsProperties;

	public QcloudTMSAutoCofiguration(QcloudTMSProperties qcloudTmsProperties) {
		this.qcloudTmsProperties = qcloudTmsProperties;
	}
}
