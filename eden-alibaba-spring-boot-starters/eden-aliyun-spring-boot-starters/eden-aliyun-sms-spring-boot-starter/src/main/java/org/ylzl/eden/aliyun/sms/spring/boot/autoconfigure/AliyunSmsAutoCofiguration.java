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

package org.ylzl.eden.aliyun.sms.spring.boot.autoconfigure;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.alibaba.cloud.spring.boot.sms.autoconfigure.SmsAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.aliyun.sms.core.AliyunSmsTemplate;
import org.ylzl.eden.dynamic.sms.spring.boot.support.SmsBeanNames;

/**
 * 阿里云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@AutoConfigureAfter(SmsAutoConfiguration.class)
@ConditionalOnProperty(value = "alibaba.cloud.sms.enabled", matchIfMissing = true)
@ConditionalOnBean(ISmsService.class)
@Slf4j
@Configuration
public class AliyunSmsAutoCofiguration {

	private static final String AUTOWIRED_ALIYUN_SMS_TEMPLATE = "Autowired AliyunSmsTemplate";

	@ConditionalOnMissingBean
	@Bean(SmsBeanNames.ALIYUN_SMS_TEMPLATE)
	public AliyunSmsTemplate aliyunSmsTemplate(ISmsService smsService) {
		log.info(AUTOWIRED_ALIYUN_SMS_TEMPLATE);
		return new AliyunSmsTemplate(smsService);
	}
}
