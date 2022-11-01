package org.ylzl.eden.spring.boot.qcloud.cos.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.spring.boot.qcloud.cos.config.QcloudCOSConfig;

/**
 * COS 配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QcloudCOSConvertor {

	QcloudCOSConvertor INSTANCE = Mappers.getMapper(QcloudCOSConvertor.class);

    QcloudCOSConfig toConfig(QcloudCOSProperties properties);
}
