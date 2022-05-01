package org.ylzl.eden.spring.boot.qcloud.tms.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.tms.env.TMSProperties;

/**
 * 腾讯云TMS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.tms.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TMSProperties.class)
@Slf4j
@Configuration
public class TMSAutoCofiguration {

	private final TMSProperties tmsProperties;

	public TMSAutoCofiguration(TMSProperties tmsProperties) {
		this.tmsProperties = tmsProperties;
	}
}
