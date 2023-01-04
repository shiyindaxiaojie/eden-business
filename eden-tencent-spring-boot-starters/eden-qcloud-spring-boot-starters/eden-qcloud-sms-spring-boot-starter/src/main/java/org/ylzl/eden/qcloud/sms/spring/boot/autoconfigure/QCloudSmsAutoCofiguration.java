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

package org.ylzl.eden.qcloud.sms.spring.boot.autoconfigure;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.sms.core.QCloudSmsTemplate;
import org.ylzl.eden.qcloud.sms.spring.boot.env.QCloudSmsProperties;
import org.ylzl.eden.spring.boot.bootstrap.constant.Conditions;

/**
 * 腾讯云短信自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(
	prefix = QCloudSmsProperties.PREFIX,
	name = Conditions.ENABLED,
	havingValue = Conditions.TRUE,
	matchIfMissing = true
)
@ConditionalOnClass(SmsClient.class)
@EnableConfigurationProperties(QCloudSmsProperties.class)
@RequiredArgsConstructor
@Slf4j
@Configuration
public class QCloudSmsAutoCofiguration {

	private static final String AUTOWIRED_QCLOUD_SMS_TEMPLATE = "Autowired QcloudSmsTemplate";

	private final QCloudSmsProperties qcloudSmsProperties;

	@ConditionalOnMissingBean
	@Bean
	public QCloudSmsTemplate qcloudSmsTemplate() {
		log.info(AUTOWIRED_QCLOUD_SMS_TEMPLATE);
		return new QCloudSmsTemplate(qcloudSmsProperties);
	}
}
