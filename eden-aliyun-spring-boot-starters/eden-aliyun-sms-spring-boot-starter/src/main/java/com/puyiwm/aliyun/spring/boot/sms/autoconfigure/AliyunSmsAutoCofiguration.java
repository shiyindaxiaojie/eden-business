package com.puyiwm.aliyun.spring.boot.sms.autoconfigure;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.alibaba.cloud.spring.boot.sms.autoconfigure.SmsAutoConfiguration;
import com.puyiwm.aliyun.spring.boot.sms.core.AliyunSmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信自动配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@AutoConfigureAfter(SmsAutoConfiguration.class)
@ConditionalOnProperty(value = "alibaba.cloud.sms.enabled", matchIfMissing = true)
@ConditionalOnBean(ISmsService.class)
@Slf4j
@Configuration
public class AliyunSmsAutoCofiguration {

	public static final String ALIYUN_SMS_TEMPLATE = "aliyunSmsTemplate";

	private static final String AUTOWIRED_ALIYUN_SMS_TEMPLATE = "Autowired AliyunSmsTemplate";

	@ConditionalOnMissingBean
	@Bean(ALIYUN_SMS_TEMPLATE)
	public AliyunSmsTemplate aliyunSmsTemplate(ISmsService smsService) {
		log.info(AUTOWIRED_ALIYUN_SMS_TEMPLATE);
		return new AliyunSmsTemplate(smsService);
	}
}