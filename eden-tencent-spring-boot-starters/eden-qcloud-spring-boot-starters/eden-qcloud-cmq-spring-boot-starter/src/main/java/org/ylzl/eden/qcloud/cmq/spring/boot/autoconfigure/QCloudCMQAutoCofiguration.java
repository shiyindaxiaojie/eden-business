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

package org.ylzl.eden.qcloud.cmq.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.ylzl.eden.qcloud.cmq.spring.boot.env.QCloudCMQProperties;
import org.ylzl.eden.spring.boot.bootstrap.constant.Conditions;

/**
 * 腾讯云CMQ 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(
	prefix = QCloudCMQProperties.PREFIX,
	name = Conditions.ENABLED,
	havingValue = Conditions.TRUE,
	matchIfMissing = true
)
@EnableConfigurationProperties(QCloudCMQProperties.class)
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class QCloudCMQAutoCofiguration {

	private final QCloudCMQProperties qcloudCmqProperties;

	public QCloudCMQAutoCofiguration(QCloudCMQProperties qcloudCmqProperties) {
		this.qcloudCmqProperties = qcloudCmqProperties;
	}
}
