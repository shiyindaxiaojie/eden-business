package org.ylzl.eden.qcloud.sms.spring.boot.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.qcloud.sms.config.QCloudSmsConfig;

/**
 * SMS 配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QCloudSmsConvertor {

	QCloudSmsConvertor INSTANCE = Mappers.getMapper(QCloudSmsConvertor.class);

    QCloudSmsConfig toConfig(QCloudSmsProperties properties);
}
