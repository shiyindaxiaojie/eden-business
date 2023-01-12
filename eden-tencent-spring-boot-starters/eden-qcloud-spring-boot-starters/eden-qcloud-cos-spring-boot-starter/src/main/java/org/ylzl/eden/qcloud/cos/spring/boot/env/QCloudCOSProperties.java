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

package org.ylzl.eden.qcloud.cos.spring.boot.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云COS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = QCloudCOSProperties.PREFIX)
public class QCloudCOSProperties {

	public static final String PREFIX = "tencent.cloud.cos";

	private boolean enabled;

	private String secretId;

	private String secretKey;

	/**
	 * 区域简称
	 *
	 * @link https://cloud.tencent.com/document/product/436/6224
	 */
	private String region;

	/**
	 * 存储桶名称
	 */
	private String bucketName;
}
