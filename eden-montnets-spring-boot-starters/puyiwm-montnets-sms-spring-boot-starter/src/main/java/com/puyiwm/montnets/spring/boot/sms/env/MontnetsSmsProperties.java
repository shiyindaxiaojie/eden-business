package com.puyiwm.montnets.spring.boot.sms.env;

import com.montnets.mwgate.common.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 梦网短信配置
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "montnets.sms")
public class MontnetsSmsProperties {

	/**
	 * 是否维持长连接
	 */
	private Boolean isKeepAlive = true;

	/**
	 * 主地址
	 */
	private List<String> address;

	/**
	 * 用户账号
	 */
	private List<String> accountInfo;

	/**
	 * 全局参数
	 */
	private final GlobalParams globalParams = new GlobalParams();

	@Data
	public static class GlobalParams {

		/**
		 * 请求路径
		 */
		private String requestPath = com.montnets.mwgate.common.GlobalParams.getInstance().getRequestPath();

		/**
		 * 是否需要日志（1：需要日志，0：不需要日志）
		 */
		private Integer needLog = com.montnets.mwgate.common.GlobalParams.getInstance().getNeedLog();

		/**
		 * HTTP 连接池的连接数量
		 */
		private Integer poolNumber = com.montnets.mwgate.common.GlobalParams.getInstance().getPoolNumber();

		/**
		 * 密码加密类型
		 */
		private PwdEncryptEnum pwdEncryptType = com.montnets.mwgate.common.GlobalParams.getInstance().getPwdEncryptType();

		private ContentEncodeEnum msgMtEncode = com.montnets.mwgate.common.GlobalParams.getInstance().getMsgMtEncode();

		private ContentEncryptEnum msgMtEncrypt = com.montnets.mwgate.common.GlobalParams.getInstance().getMsgMtEncrypt();

		private SecretKeyEnum mtKey = com.montnets.mwgate.common.GlobalParams.getInstance().getMtKey();

		private String mtFixedKey = com.montnets.mwgate.common.GlobalParams.getInstance().getMtFixedKey();

		private ContentEncodeEnum msgMoEncode = com.montnets.mwgate.common.GlobalParams.getInstance().getMsgMoEncode();

		private ContentEncryptEnum msgMoEncrypt = com.montnets.mwgate.common.GlobalParams.getInstance().getMsgMoEncrypt();

		private SecretKeyEnum moKey = com.montnets.mwgate.common.GlobalParams.getInstance().getMoKey();

		private String moFixedKey = com.montnets.mwgate.common.GlobalParams.getInstance().getMoFixedKey();
	}
}