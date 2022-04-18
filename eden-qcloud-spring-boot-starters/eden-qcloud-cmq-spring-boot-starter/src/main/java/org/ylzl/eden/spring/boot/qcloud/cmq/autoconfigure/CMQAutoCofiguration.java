package org.ylzl.eden.spring.boot.qcloud.cmq.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.cmq.env.CMQProperties;

/**
 * 腾讯云CMQ 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.cmq.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(CMQProperties.class)
@Slf4j
@Configuration
public class CMQAutoCofiguration {

	private final CMQProperties cmqProperties;

	public CMQAutoCofiguration(CMQProperties cmqProperties) {
		this.cmqProperties = cmqProperties;
	}
}
