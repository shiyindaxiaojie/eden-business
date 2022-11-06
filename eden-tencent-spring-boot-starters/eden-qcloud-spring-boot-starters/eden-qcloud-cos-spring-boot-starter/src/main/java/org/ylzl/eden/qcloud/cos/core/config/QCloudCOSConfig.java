package org.ylzl.eden.qcloud.cos.core.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * COS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public class QCloudCOSConfig {

	private String secretId;

	private String secretKey;

	private String region;

	private String bucketName;
}
