package org.ylzl.eden.spring.boot.qcloud.vod.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.vod.config.QcloudVODConfig;
import org.ylzl.eden.spring.boot.qcloud.vod.core.QcloudVODTemplate;
import org.ylzl.eden.spring.boot.qcloud.vod.env.QcloudVODProperties;

/**
 * 腾讯云VOD 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.vod.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QcloudVODProperties.class)
@Slf4j
@Configuration
public class QcloudVODAutoCofiguration {

	private final QcloudVODProperties qcloudVodProperties;

	public QcloudVODAutoCofiguration(QcloudVODProperties qcloudVodProperties) {
		this.qcloudVodProperties = qcloudVodProperties;
	}

	@Bean
	public QcloudVODTemplate vodTemplate() {
		log.info("Autowired VODTemplate");
		return new QcloudVODTemplate(
			QcloudVODConfig.builder()
				.region(qcloudVodProperties.getRegion())
				.secretId(qcloudVodProperties.getSecretId())
				.secretKey(qcloudVodProperties.getSecretKey())
				.shortignValidDuration(qcloudVodProperties.getShortignValidDuration()).build());
	}
}
