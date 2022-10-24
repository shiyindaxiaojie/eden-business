package org.ylzl.eden.spring.boot.emay.sms.autoconfigure;

import cn.emay.sdk.client.SmsSDKClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.sms.adapter.core.SmsType;
import org.ylzl.eden.spring.boot.emay.sms.core.EmaySmsTemplate;
import org.ylzl.eden.spring.boot.emay.sms.env.EmaySmsProperties;

/**
 * 亿美短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = EmaySmsProperties.ENABLED, matchIfMissing = true)
@ConditionalOnBean(SmsSDKClient.class)
@EnableConfigurationProperties(EmaySmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration
public class EmaySmsAutoCofiguration {

	private static final String AUTOWIRED_EMAY_SMS_TEMPLATE = "Autowired emaySmsTemplate";

	private final EmaySmsProperties emaySmsProperties;

	@ConditionalOnMissingBean
	@Bean(SmsType.EMAY_SMS_TEMPLATE)
	public EmaySmsTemplate emaySmsTemplate() {
		log.info(AUTOWIRED_EMAY_SMS_TEMPLATE);
		return new EmaySmsTemplate(emaySmsProperties.getSms());
	}
}
