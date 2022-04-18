package com.puyiwm.montnets.spring.boot.sms.autoconfigure;

import com.montnets.mwgate.smsutil.SmsSendConn;
import com.puyiwm.montnets.spring.boot.sms.core.MontnetsSmsTemplate;
import com.puyiwm.montnets.spring.boot.sms.env.MontnetsSmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 梦网短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "montnets.sms.enabled", matchIfMissing = true)
@ConditionalOnBean(SmsSendConn.class)
@EnableConfigurationProperties(MontnetsSmsProperties.class)
@Slf4j
@Configuration
public class MontnetsSmsAutoCofiguration {

	public static final String MONTNETS_TEMPLATE = "montnetsSmsTemplate";

	private static final String AUTOWIRED_MONTNETS_SMS_TEMPLATE = "Autowired MontnetsSmsTemplate";

	private final MontnetsSmsProperties montnetsSmsProperties;

	public MontnetsSmsAutoCofiguration(MontnetsSmsProperties montnetsSmsProperties) {
		this.montnetsSmsProperties = montnetsSmsProperties;
	}
	@ConditionalOnMissingBean
	@Bean(MONTNETS_TEMPLATE)
	public MontnetsSmsTemplate montnetsSmsTemplate() {
		log.info(AUTOWIRED_MONTNETS_SMS_TEMPLATE);
		return new MontnetsSmsTemplate(montnetsSmsProperties);
	}
}
