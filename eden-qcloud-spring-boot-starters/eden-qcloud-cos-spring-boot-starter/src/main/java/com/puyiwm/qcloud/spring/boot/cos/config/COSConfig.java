package com.puyiwm.qcloud.spring.boot.cos.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * COS 配置
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2021-12-27
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
