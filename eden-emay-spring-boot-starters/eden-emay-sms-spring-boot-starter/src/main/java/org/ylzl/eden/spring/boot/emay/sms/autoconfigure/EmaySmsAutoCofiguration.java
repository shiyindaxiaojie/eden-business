package org.ylzl.eden.spring.boot.emay.sms.autoconfigure;

import cn.emay.sdk.client.SmsSDKClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.mail.adapter.core.SmsTemplateFactory;
import org.ylzl.eden.spring.boot.emay.sms.core.EmaySmsTemplate;
import org.ylzl.eden.spring.boot.emay.sms.env.EmaySmsProperties;

/**
 * 亿美短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = EmaySmsAutoCofiguration.ENABLED, matchIfMissing = true)
@ConditionalOnBean(SmsSDKClient.class)
@EnableConfigurationProperties(EmaySmsProperties.class)
@Slf4j
@Configuration
public class EmaySmsAutoCofiguration {

	public static final String ENABLED = "emay.sms.enabled";

	public static final String TYPE = "EMAY";

	public static final String BEAN = "emaySmsTemplate";

	private static final String AUTOWIRED_EMAY_SMS_TEMPLATE = "Autowired emaySmsTemplate";

	private final EmaySmsProperties emaySmsProperties;

	public EmaySmsAutoCofiguration(EmaySmsProperties emaySmsProperties) {
		this.emaySmsProperties = emaySmsProperties;
	}

	@ConditionalOnMissingBean
	@Bean(BEAN)
	public EmaySmsTemplate emaySmsTemplate() {
		log.info(AUTOWIRED_EMAY_SMS_TEMPLATE);
		SmsTemplateFactory.addBean(TYPE, BEAN);
		return new EmaySmsTemplate(emaySmsProperties);
	}
}
