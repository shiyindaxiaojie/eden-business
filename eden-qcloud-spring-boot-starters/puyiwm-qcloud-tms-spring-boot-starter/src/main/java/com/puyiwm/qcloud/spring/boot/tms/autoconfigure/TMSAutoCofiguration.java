package com.puyiwm.qcloud.spring.boot.tms.autoconfigure;

import com.puyiwm.qcloud.spring.boot.tms.env.TMSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云TMS 自动配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${qcloud.tms.enabled:false}")
@EnableConfigurationProperties(TMSProperties.class)
@Slf4j
@Configuration
public class TMSAutoCofiguration {

	private final TMSProperties tmsProperties;

	public TMSAutoCofiguration(TMSProperties tmsProperties) {
		this.tmsProperties = tmsProperties;
	}
}
