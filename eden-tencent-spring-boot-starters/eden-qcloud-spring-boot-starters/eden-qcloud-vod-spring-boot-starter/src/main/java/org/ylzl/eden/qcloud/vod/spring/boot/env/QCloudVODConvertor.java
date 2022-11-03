package org.ylzl.eden.qcloud.vod.spring.boot.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.qcloud.vod.core.config.QCloudVODConfig;

/**
 * VOD 配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QCloudVODConvertor {

	QCloudVODConvertor INSTANCE = Mappers.getMapper(QCloudVODConvertor.class);

    QCloudVODConfig toConfig(QCloudVODProperties properties);
}
