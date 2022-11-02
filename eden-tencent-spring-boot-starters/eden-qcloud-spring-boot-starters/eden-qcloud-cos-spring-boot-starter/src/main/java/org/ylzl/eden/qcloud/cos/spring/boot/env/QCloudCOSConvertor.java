package org.ylzl.eden.qcloud.cos.spring.boot.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.qcloud.cos.config.QCloudCOSConfig;

/**
 * COS 配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QCloudCOSConvertor {

	QCloudCOSConvertor INSTANCE = Mappers.getMapper(QCloudCOSConvertor.class);

    QCloudCOSConfig toConfig(QCloudCOSProperties properties);
}
