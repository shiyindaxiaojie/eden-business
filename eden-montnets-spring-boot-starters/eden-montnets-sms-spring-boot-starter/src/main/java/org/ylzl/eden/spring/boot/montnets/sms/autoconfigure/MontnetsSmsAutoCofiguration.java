package org.ylzl.eden.spring.boot.montnets.sms.autoconfigure;

import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.common.sms.autoconfigure.SmsBeanType;
import org.ylzl.eden.spring.boot.montnets.sms.core.MontnetsSmsTemplate;
import org.ylzl.eden.spring.boot.montnets.sms.env.MontnetsSmsConvertor;
import org.ylzl.eden.spring.boot.montnets.sms.env.MontnetsSmsProperties;

/**
 * 梦网短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = MontnetsSmsProperties.ENABLED, matchIfMissing = true)
@ConditionalOnBean(SmsSendConn.class)
@EnableConfigurationProperties(MontnetsSmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration
public class MontnetsSmsAutoCofiguration {

	private static final String AUTOWIRED_MONTNETS_SMS_TEMPLATE = "Autowired MontnetsSmsTemplate";

	private final MontnetsSmsProperties montnetsSmsProperties;

	@ConditionalOnMissingBean
	@Bean(SmsBeanType.MONTNETS_SMS_TEMPLATE)
	public MontnetsSmsTemplate montnetsSmsTemplate() {
		log.info(AUTOWIRED_MONTNETS_SMS_TEMPLATE);
		return new MontnetsSmsTemplate(MontnetsSmsConvertor.INSTANCE.toConfig(montnetsSmsProperties));
	}
}
