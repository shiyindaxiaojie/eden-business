package com.puyiwm.qcloud.spring.boot.cos.autoconfigure;

import com.puyiwm.qcloud.spring.boot.cos.config.COSConfig;
import com.puyiwm.qcloud.spring.boot.cos.core.COSTemplate;
import com.puyiwm.qcloud.spring.boot.cos.env.COSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@ConditionalOnExpression("${tencent.cloud.cos.enabled:true}")
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
