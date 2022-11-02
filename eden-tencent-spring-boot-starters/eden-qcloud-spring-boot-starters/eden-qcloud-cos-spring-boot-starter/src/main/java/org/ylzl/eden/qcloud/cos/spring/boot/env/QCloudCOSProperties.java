package org.ylzl.eden.qcloud.cos.spring.boot.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云COS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.cos")
public class QCloudCOSProperties {

	private boolean enabled;

	private String secretId;

	private String secretKey;

	/**
	 * 区域简称
	 *
	 * @link https://cloud.tencent.com/document/product/436/6224
	 */
	private String region;

	/**
	 * 存储桶名称
	 */
	private String bucketName;
}
