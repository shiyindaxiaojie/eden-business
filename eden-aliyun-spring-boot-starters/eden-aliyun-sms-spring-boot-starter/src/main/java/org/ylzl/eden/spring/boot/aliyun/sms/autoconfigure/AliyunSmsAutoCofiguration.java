package org.ylzl.eden.spring.boot.aliyun.sms.autoconfigure;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.alibaba.cloud.spring.boot.sms.autoconfigure.SmsAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.mail.adapter.core.SmsTemplateFactory;
import org.ylzl.eden.spring.boot.aliyun.sms.core.AliyunSmsTemplate;

/**
 * 阿里云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@AutoConfigureAfter(SmsAutoConfiguration.class)
@ConditionalOnProperty(value = AliyunSmsAutoCofiguration.ENABLED, matchIfMissing = true)
@ConditionalOnBean(ISmsService.class)
@Slf4j
@Configuration
public class AliyunSmsAutoCofiguration {

	public static final String ENABLED = "alibaba.cloud.sms.enabled";

	public static final String TYPE = "ALIYUN";

	public static final String BEAN = "aliyunSmsTemplate";

	private static final String AUTOWIRED_ALIYUN_SMS_TEMPLATE = "Autowired AliyunSmsTemplate";

	@ConditionalOnMissingBean
	@Bean(BEAN)
	public AliyunSmsTemplate aliyunSmsTemplate(ISmsService smsService) {
		log.info(AUTOWIRED_ALIYUN_SMS_TEMPLATE);
		SmsTemplateFactory.addBean(TYPE, BEAN);
		return new AliyunSmsTemplate(smsService);
	}
}
