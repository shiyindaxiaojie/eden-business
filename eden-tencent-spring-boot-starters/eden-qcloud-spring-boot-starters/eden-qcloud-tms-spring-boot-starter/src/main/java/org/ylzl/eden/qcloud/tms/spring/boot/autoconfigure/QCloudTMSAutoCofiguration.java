package org.ylzl.eden.qcloud.tms.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.tms.spring.boot.env.QCloudTMSProperties;

/**
 * 腾讯云TMS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.tms.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudTMSProperties.class)
@Slf4j
@Configuration
public class QCloudTMSAutoCofiguration {

	private final QCloudTMSProperties qcloudTmsProperties;

	public QCloudTMSAutoCofiguration(QCloudTMSProperties qcloudTmsProperties) {
		this.qcloudTmsProperties = qcloudTmsProperties;
	}
}
