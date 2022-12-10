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

package org.ylzl.eden.emay.sms.spring.boot.autoconfigure;

import cn.emay.sdk.client.SmsSDKClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.dynamic.sms.spring.boot.support.SmsBeanNames;
import org.ylzl.eden.emay.sms.core.EmaySmsTemplate;
import org.ylzl.eden.emay.sms.spring.boot.env.EmaySmsConvertor;
import org.ylzl.eden.emay.sms.spring.boot.env.EmaySmsProperties;

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
	@Bean(SmsBeanNames.EMAY_SMS_TEMPLATE)
	public EmaySmsTemplate emaySmsTemplate() {
		log.info(AUTOWIRED_EMAY_SMS_TEMPLATE);
		return new EmaySmsTemplate(EmaySmsConvertor.INSTANCE.toConfig(emaySmsProperties));
	}
}
