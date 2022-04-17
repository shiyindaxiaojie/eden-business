package com.puyiwm.qcloud.spring.boot.vod.config;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * VOD 配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2021-12-27
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
