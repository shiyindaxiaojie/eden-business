package org.ylzl.eden.spring.boot.cos.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * COS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public class COSConfig {

	private String secretId;

	private String secretKey;

	private String region;

	private String bucketName;
}

