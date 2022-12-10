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

package org.ylzl.eden.qcloud.cos.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.cos.core.QCloudCOSTemplate;
import org.ylzl.eden.qcloud.cos.spring.boot.env.QCloudCOSConvertor;
import org.ylzl.eden.qcloud.cos.spring.boot.env.QCloudCOSProperties;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.cos.enabled", havingValue =
	"true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudCOSProperties.class)
@Slf4j
@Configuration
public class QCloudCOSAutoCofiguration {

	private final QCloudCOSProperties qcloudCosProperties;

	public QCloudCOSAutoCofiguration(QCloudCOSProperties qcloudCosProperties) {
		this.qcloudCosProperties = qcloudCosProperties;
	}

	@Bean
	public QCloudCOSTemplate cosTemplate() {
		log.info("Autowired COSTemplate");
		return new QCloudCOSTemplate(QCloudCOSConvertor.INSTANCE.toConfig(qcloudCosProperties));
	}
}
