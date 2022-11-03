package org.ylzl.eden.montnets.sms.spring.boot.env;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.ylzl.eden.montnets.sms.core.config.MontnetsSmsConfig;

/**
 * 梦网短信配置转换器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MontnetsSmsConvertor {

	MontnetsSmsConvertor INSTANCE = Mappers.getMapper(MontnetsSmsConvertor.class);

	MontnetsSmsConfig toConfig(MontnetsSmsProperties properties);
}
