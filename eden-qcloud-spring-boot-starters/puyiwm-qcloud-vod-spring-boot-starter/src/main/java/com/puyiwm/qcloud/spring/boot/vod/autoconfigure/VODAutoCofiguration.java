package com.puyiwm.qcloud.spring.boot.vod.autoconfigure;

import com.puyiwm.qcloud.spring.boot.vod.config.VODConfig;
import com.puyiwm.qcloud.spring.boot.vod.core.VODTemplate;
import com.puyiwm.qcloud.spring.boot.vod.env.VODProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云VOD 自动配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${tencent.cloud.vod.enabled:true}")
@EnableConfigurationProperties(VODProperties.class)
@Slf4j
@Configuration
public class VODAutoCofiguration {

	private final VODProperties vodProperties;

	public VODAutoCofiguration(VODProperties vodProperties) {
		this.vodProperties = vodProperties;
	}

	@Bean
	public VODTemplate vodTemplate() {
		log.info("Autowired VODTemplate");
		return new VODTemplate(
			VODConfig.builder()
				.region(vodProperties.getRegion())
				.secretId(vodProperties.getSecretId())
				.secretKey(vodProperties.getSecretKey())
				.shortignValidDuration(vodProperties.getShortignValidDuration()).build());
	}
}
