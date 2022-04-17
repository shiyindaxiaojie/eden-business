package com.puyiwm.qcloud.spring.boot.cos.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云COS 配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@Data
@ConfigurationProperties(prefix = "tencent.cloud.cos")
public class COSProperties {

	private Boolean enabled;

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