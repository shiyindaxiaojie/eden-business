package org.ylzl.eden.spring.boot.qcloud.sms.autoconfigure;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.sms.config.QcloudSmsConfig;
import org.ylzl.eden.spring.boot.qcloud.sms.core.QcloudSmsTemplate;
import org.ylzl.eden.spring.boot.qcloud.sms.env.QcloudSmsProperties;

/**
 * 腾讯云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.sms.enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(SmsClient.class)
@EnableConfigurationProperties(QcloudSmsProperties.class)
@Slf4j
@Configuration
public class QcloudSmsAutoCofiguration {

	public static final String QCLOUD_SMS_TEMPLATE = "qcloudSmsTemplate";

	private static final String AUTOWIRED_TENCENT_CLOUD_SMS_TEMPLATE = "Autowired TencentCloudSmsTemplate";

	private final QcloudSmsProperties qcloudSmsProperties;

	public QcloudSmsAutoCofiguration(QcloudSmsProperties qcloudSmsProperties) {
		this.qcloudSmsProperties = qcloudSmsProperties;
	}

	@ConditionalOnMissingBean
	@Bean(QCLOUD_SMS_TEMPLATE)
	public QcloudSmsTemplate qcloudSmsTemplate() {
		log.info(AUTOWIRED_TENCENT_CLOUD_SMS_TEMPLATE);
		return new QcloudSmsTemplate(QcloudSmsConfig.builder()
			.smsSdkAppId(qcloudSmsProperties.getSmsSdkAppId())
			.secretKey(qcloudSmsProperties.getSecretKey())
			.accessKey(qcloudSmsProperties.getAccessKey())
			.region(qcloudSmsProperties.getRegion())
			.build());
	}
}
