package org.ylzl.eden.spring.boot.vod.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * VOD 配置
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
public class VODConfig {

	private String secretId;

	private String secretKey;

	private String region;

	private Integer shortignValidDuration;
}


