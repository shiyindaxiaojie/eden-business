package org.ylzl.eden.qcloud.sms.spring.boot.autoconfigure;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.common.sms.spring.boot.autoconfigure.factory.SmsBeanType;
import org.ylzl.eden.qcloud.sms.core.QCloudSmsTemplate;
import org.ylzl.eden.qcloud.sms.spring.boot.env.QCloudSmsConvertor;
import org.ylzl.eden.qcloud.sms.spring.boot.env.QCloudSmsProperties;

/**
 * 腾讯云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = QCloudSmsProperties.ENABLED, havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(SmsClient.class)
@EnableConfigurationProperties(QCloudSmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration
public class QCloudSmsAutoCofiguration {

	private static final String AUTOWIRED_QCLOUD_SMS_TEMPLATE = "Autowired QcloudSmsTemplate";

	private final QCloudSmsProperties qcloudSmsProperties;

	@ConditionalOnMissingBean
	@Bean(SmsBeanType.QCLOUD_SMS_TEMPLATE)
	public QCloudSmsTemplate qcloudSmsTemplate() {
		log.info(AUTOWIRED_QCLOUD_SMS_TEMPLATE);
		return new QCloudSmsTemplate(QCloudSmsConvertor.INSTANCE.toConfig(qcloudSmsProperties));
	}
}
