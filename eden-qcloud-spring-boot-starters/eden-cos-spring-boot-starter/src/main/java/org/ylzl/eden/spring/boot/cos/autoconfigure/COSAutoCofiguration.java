package org.ylzl.eden.spring.boot.cos.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.cos.core.COSConfig;
import org.ylzl.eden.spring.boot.cos.core.COSTemplate;
import org.ylzl.eden.spring.boot.cos.env.COSProperties;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${qcloud.cos.enabled:false}")
@EnableConfigurationProperties(COSProperties.class)
@Slf4j
@Configuration
public class COSAutoCofiguration {

	private static final String AUTOWIRED_COS_TEMPLATE = "Autowired COSTemplate";

	private final COSProperties cosProperties;

	public COSAutoCofiguration(COSProperties cosProperties) {
		this.cosProperties = cosProperties;
	}

	@Bean
	public COSTemplate cosTemplate() {
		log.info(AUTOWIRED_COS_TEMPLATE);
		return new COSTemplate(
			COSConfig.builder()
				.region(cosProperties.getRegion())
				.bucketName(cosProperties.getBucketName())
				.secretId(cosProperties.getSecretId())
				.secretKey(cosProperties.getSecretKey()).build());
	}
}
