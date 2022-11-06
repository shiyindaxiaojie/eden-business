package org.ylzl.eden.qcloud.cos.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ylzl.eden.qcloud.cos.core.QCloudCOSTemplate;
import org.ylzl.eden.qcloud.cos.spring.boot.env.QCloudCOSConvertor;
import org.ylzl.eden.qcloud.cos.spring.boot.env.QCloudCOSProperties;

/**
 * 腾讯云COS 自动配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@ConditionalOnProperty(value = "tencent.cloud.cos.enabled", havingValue =
	"true", matchIfMissing = true)
@EnableConfigurationProperties(QCloudCOSProperties.class)
@Slf4j
@Configuration
public class QCloudCOSAutoCofiguration {

	private final QCloudCOSProperties qcloudCosProperties;

	public QCloudCOSAutoCofiguration(QCloudCOSProperties qcloudCosProperties) {
		this.qcloudCosProperties = qcloudCosProperties;
	}

	@Bean
	public QCloudCOSTemplate cosTemplate() {
		log.info("Autowired COSTemplate");
		return new QCloudCOSTemplate(QCloudCOSConvertor.INSTANCE.toConfig(qcloudCosProperties));
	}
}
