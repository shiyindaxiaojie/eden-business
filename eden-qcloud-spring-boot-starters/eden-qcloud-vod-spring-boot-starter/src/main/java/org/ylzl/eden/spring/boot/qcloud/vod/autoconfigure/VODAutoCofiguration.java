package org.ylzl.eden.spring.boot.qcloud.vod.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.vod.config.VODConfig;
import org.ylzl.eden.spring.boot.qcloud.vod.core.VODTemplate;
import org.ylzl.eden.spring.boot.qcloud.vod.env.VODProperties;

/**
 * 腾讯云VOD 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.vod.enabled", havingValue = "true", matchIfMissing = true)
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
