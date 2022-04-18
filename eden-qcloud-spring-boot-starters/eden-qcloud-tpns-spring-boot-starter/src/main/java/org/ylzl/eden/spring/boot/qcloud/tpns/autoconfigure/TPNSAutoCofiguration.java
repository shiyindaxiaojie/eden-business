package org.ylzl.eden.spring.boot.qcloud.tpns.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.tpns.env.TPNSProperties;

/**
 * 腾讯云TPNS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.tpns.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TPNSProperties.class)
@Slf4j
@Configuration
public class TPNSAutoCofiguration {

	private final TPNSProperties tpnsProperties;

	public TPNSAutoCofiguration(TPNSProperties tpnsProperties) {
		this.tpnsProperties = tpnsProperties;
	}
}
