package com.puyiwm.qcloud.spring.boot.cmq.autoconfigure;

import com.puyiwm.qcloud.spring.boot.cmq.env.CMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云CMQ 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${qcloud.cmq.enabled:false}")
@EnableConfigurationProperties(CMQProperties.class)
@Slf4j
@Configuration
public class CMQAutoCofiguration {

	private final CMQProperties cmqProperties;

	public CMQAutoCofiguration(CMQProperties cmqProperties) {
		this.cmqProperties = cmqProperties;
	}
}
