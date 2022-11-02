package org.ylzl.eden.qcloud.cmq.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.cmq.spring.boot.env.QCloudCMQProperties;

/**
 * 腾讯云CMQ 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.cmq.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudCMQProperties.class)
@Slf4j
@Configuration
public class QCloudCMQAutoCofiguration {

	private final QCloudCMQProperties qcloudCmqProperties;

	public QCloudCMQAutoCofiguration(QCloudCMQProperties qcloudCmqProperties) {
		this.qcloudCmqProperties = qcloudCmqProperties;
	}
}
