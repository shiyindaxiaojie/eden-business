/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.montnets.sms.spring.boot.autoconfigure;

import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.ylzl.eden.montnets.sms.core.MontnetsSmsTemplate;
import org.ylzl.eden.montnets.sms.spring.boot.env.MontnetsSmsProperties;
import org.ylzl.eden.spring.boot.bootstrap.constant.Conditions;

/**
 * 梦网短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(
	prefix = MontnetsSmsProperties.PREFIX,
	name = Conditions.ENABLED,
	havingValue = Conditions.TRUE,
	matchIfMissing = true
)
@ConditionalOnBean(SmsSendConn.class)
@EnableConfigurationProperties(MontnetsSmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class MontnetsSmsAutoCofiguration {

	private static final String AUTOWIRED_MONTNETS_SMS_TEMPLATE = "Autowired MontnetsSmsTemplate";

	private final MontnetsSmsProperties montnetsSmsProperties;

	@ConditionalOnMissingBean
	@Bean
	public MontnetsSmsTemplate montnetsSmsTemplate() {
		log.info(AUTOWIRED_MONTNETS_SMS_TEMPLATE);
		return new MontnetsSmsTemplate(montnetsSmsProperties);
	}
}
