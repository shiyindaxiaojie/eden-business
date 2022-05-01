package org.ylzl.eden.spring.boot.qcloud.cos.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.cos.env.COSProperties;
import org.ylzl.eden.spring.boot.qcloud.cos.config.COSConfig;
import org.ylzl.eden.spring.boot.qcloud.cos.core.COSTemplate;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnProperty(value = "tencent.cloud.cos.enabled", havingValue =
	"true", matchIfMissing = true)
@EnableConfigurationProperties(COSProperties.class)
@Slf4j
@Configuration
public class COSAutoCofiguration {

	private final COSProperties cosProperties;

	public COSAutoCofiguration(COSProperties cosProperties) {
		this.cosProperties = cosProperties;
	}

	@Bean
	public COSTemplate cosTemplate() {
		log.info("Autowired COSTemplate");
		return new COSTemplate(
			COSConfig.builder()
				.region(cosProperties.getRegion())
				.bucketName(cosProperties.getBucketName())
				.secretId(cosProperties.getSecretId())
				.secretKey(cosProperties.getSecretKey()).build());
	}
}
