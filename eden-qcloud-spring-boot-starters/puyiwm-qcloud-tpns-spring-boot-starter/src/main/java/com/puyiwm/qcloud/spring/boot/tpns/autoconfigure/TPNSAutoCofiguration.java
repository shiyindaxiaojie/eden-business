package com.puyiwm.qcloud.spring.boot.tpns.autoconfigure;

import com.puyiwm.qcloud.spring.boot.tpns.env.TPNSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云TPNS 自动配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${qcloud.tpns.enabled:false}")
@EnableConfigurationProperties(TPNSProperties.class)
@Slf4j
@Configuration
public class TPNSAutoCofiguration {

	private final TPNSProperties tpnsProperties;

	public TPNSAutoCofiguration(TPNSProperties tpnsProperties) {
		this.tpnsProperties = tpnsProperties;
	}
}
