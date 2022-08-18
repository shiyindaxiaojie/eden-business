package org.ylzl.eden.spring.boot.montnets.sms.autoconfigure;

import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.montnets.sms.core.MontnetsSmsTemplate;
import org.ylzl.eden.spring.boot.montnets.sms.env.MontnetsSmsProperties;

/**
 * 梦网短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
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
