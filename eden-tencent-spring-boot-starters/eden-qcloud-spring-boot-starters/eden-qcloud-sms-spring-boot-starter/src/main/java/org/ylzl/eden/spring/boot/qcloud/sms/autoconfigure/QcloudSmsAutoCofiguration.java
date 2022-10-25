package org.ylzl.eden.spring.boot.qcloud.sms.autoconfigure;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.common.sms.core.SmsType;
import org.ylzl.eden.spring.boot.qcloud.sms.core.QcloudSmsTemplate;
import org.ylzl.eden.spring.boot.qcloud.sms.env.QcloudSmsProperties;

/**
 * 腾讯云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = QcloudSmsProperties.ENABLED, havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(SmsClient.class)
@EnableConfigurationProperties(QcloudSmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration
public class QcloudSmsAutoCofiguration {

	private static final String AUTOWIRED_QCLOUD_SMS_TEMPLATE = "Autowired QcloudSmsTemplate";

	private final QcloudSmsProperties qcloudSmsProperties;

	@ConditionalOnMissingBean
	@Bean(SmsType.QCLOUD_SMS_TEMPLATE)
	public QcloudSmsTemplate qcloudSmsTemplate() {
		log.info(AUTOWIRED_QCLOUD_SMS_TEMPLATE);
		return new QcloudSmsTemplate(qcloudSmsProperties.getSms());
	}
}
