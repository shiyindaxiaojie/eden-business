package org.ylzl.eden.qcloud.vod.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.vod.spring.boot.env.QCloudVODProperties;
import org.ylzl.eden.qcloud.vod.config.QCloudVODConfig;
import org.ylzl.eden.qcloud.vod.core.QCloudVODTemplate;

/**
 * 腾讯云VOD 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.vod.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudVODProperties.class)
@Slf4j
@Configuration
public class QCloudVODAutoCofiguration {

	private final QCloudVODProperties qcloudVodProperties;

	public QCloudVODAutoCofiguration(QCloudVODProperties qcloudVodProperties) {
		this.qcloudVodProperties = qcloudVodProperties;
	}

	@Bean
	public QCloudVODTemplate vodTemplate() {
		log.info("Autowired VODTemplate");
		return new QCloudVODTemplate(
			QCloudVODConfig.builder()
				.region(qcloudVodProperties.getRegion())
				.secretId(qcloudVodProperties.getSecretId())
				.secretKey(qcloudVodProperties.getSecretKey())
				.shortignValidDuration(qcloudVodProperties.getShortignValidDuration()).build());
	}
}
