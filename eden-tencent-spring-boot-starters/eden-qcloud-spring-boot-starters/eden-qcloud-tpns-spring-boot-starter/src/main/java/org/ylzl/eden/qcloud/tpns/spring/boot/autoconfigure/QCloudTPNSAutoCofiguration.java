package org.ylzl.eden.qcloud.tpns.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.tpns.spring.boot.env.QCloudTPNSProperties;

/**
 * 腾讯云TPNS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.tpns.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudTPNSProperties.class)
@Slf4j
@Configuration
public class QCloudTPNSAutoCofiguration {

	private final QCloudTPNSProperties qcloudTPNSProperties;

	public QCloudTPNSAutoCofiguration(QCloudTPNSProperties qcloudTPNSProperties) {
		this.qcloudTPNSProperties = qcloudTPNSProperties;
	}
}
