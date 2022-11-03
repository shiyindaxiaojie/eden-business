package org.ylzl.eden.emay.sms.spring.boot.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.emay.sms.core.config.EmaySmsConfig;

/**
 * 亿美短信配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmaySmsConvertor {

	EmaySmsConvertor INSTANCE = Mappers.getMapper(EmaySmsConvertor.class);

	EmaySmsConfig toConfig(EmaySmsProperties properties);
}
