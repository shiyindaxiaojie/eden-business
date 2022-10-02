package org.ylzl.eden.spring.boot.montnets.sms.autoconfigure;

import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.mail.adapter.core.SmsTemplateFactory;
import org.ylzl.eden.spring.boot.montnets.sms.core.MontnetsSmsTemplate;
import org.ylzl.eden.spring.boot.montnets.sms.env.MontnetsSmsProperties;

/**
 * 梦网短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = MontnetsSmsAutoCofiguration.ENABLED, matchIfMissing = true)
@ConditionalOnBean(SmsSendConn.class)
@EnableConfigurationProperties(MontnetsSmsProperties.class)
@Slf4j
@Configuration
public class MontnetsSmsAutoCofiguration {

	public static final String ENABLED = "montnets.sms.enabled";

	public static final String TYPE = "MONTNETS";

	public static final String BEAN = "montnetsSmsTemplate";

	private static final String AUTOWIRED_MONTNETS_SMS_TEMPLATE = "Autowired MontnetsSmsTemplate";

	private final MontnetsSmsProperties montnetsSmsProperties;

	public MontnetsSmsAutoCofiguration(MontnetsSmsProperties montnetsSmsProperties) {
		this.montnetsSmsProperties = montnetsSmsProperties;
	}

	@ConditionalOnMissingBean
	@Bean(BEAN)
	public MontnetsSmsTemplate montnetsSmsTemplate() {
		log.info(AUTOWIRED_MONTNETS_SMS_TEMPLATE);
		SmsTemplateFactory.addBean(TYPE, BEAN);
		return new MontnetsSmsTemplate(montnetsSmsProperties);
	}
}
