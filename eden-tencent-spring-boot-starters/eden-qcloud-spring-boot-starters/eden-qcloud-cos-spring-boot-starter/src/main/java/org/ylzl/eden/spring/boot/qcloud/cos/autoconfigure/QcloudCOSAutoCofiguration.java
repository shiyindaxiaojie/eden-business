package org.ylzl.eden.spring.boot.qcloud.cos.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.spring.boot.qcloud.cos.core.QcloudCOSTemplate;
import org.ylzl.eden.spring.boot.qcloud.cos.env.QcloudCOSConvertor;
import org.ylzl.eden.spring.boot.qcloud.cos.env.QcloudCOSProperties;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.cos.enabled", havingValue =
	"true", matchIfMissing = true)
@EnableConfigurationProperties(QcloudCOSProperties.class)
@Slf4j
@Configuration
public class QcloudCOSAutoCofiguration {

	private final QcloudCOSProperties qcloudCosProperties;

	public QcloudCOSAutoCofiguration(QcloudCOSProperties qcloudCosProperties) {
		this.qcloudCosProperties = qcloudCosProperties;
	}

	@Bean
	public QcloudCOSTemplate cosTemplate() {
		log.info("Autowired COSTemplate");
		return new QcloudCOSTemplate(QcloudCOSConvertor.INSTANCE.toConfig(qcloudCosProperties));
	}
}
