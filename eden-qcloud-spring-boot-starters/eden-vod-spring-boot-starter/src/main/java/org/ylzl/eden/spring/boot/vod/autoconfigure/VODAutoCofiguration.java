package org.ylzl.eden.spring.boot.vod.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.vod.core.VODConfig;
import org.ylzl.eden.spring.boot.vod.core.VODTemplate;
import org.ylzl.eden.spring.boot.vod.env.VODProperties;

/**
 * 腾讯云 VOD 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${qcloud.vod.enabled:false}")
@EnableConfigurationProperties(VODProperties.class)
@Slf4j
@Configuration
public class VODAutoCofiguration {

	private static final String AUTOWIRED_VOD_TEMPLATE = "Autowired VODTemplate";

	private final VODProperties vodProperties;

	public VODAutoCofiguration(VODProperties vodProperties) {
		this.vodProperties = vodProperties;
	}

	@Bean
	public VODTemplate vodTemplate() {
		log.info(AUTOWIRED_VOD_TEMPLATE);
		return new VODTemplate(
			VODConfig.builder()
				.region(vodProperties.getRegion())
				.secretId(vodProperties.getSecretId())
				.secretKey(vodProperties.getSecretKey())
				.shortignValidDuration(vodProperties.getShortignValidDuration()).build());
	}
}
