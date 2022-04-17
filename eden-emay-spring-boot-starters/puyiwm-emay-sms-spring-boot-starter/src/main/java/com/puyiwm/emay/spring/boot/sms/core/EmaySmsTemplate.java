package com.puyiwm.emay.spring.boot.sms.core;

import cn.emay.sdk.util.DateUtil;
import cn.emay.sdk.util.Md5;
import com.puyiwm.emay.spring.boot.sms.constant.EmayPlatform;
import com.puyiwm.emay.spring.boot.sms.env.EmaySmsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * 亿美短信操作模板
 *
 * @author <a href="mailto:guoyuanlu@puyiwm.com">gyl</a>
 * @since 2.4.x
 */
@RequiredArgsConstructor
@Slf4j
public class EmaySmsTemplate implements SmsTemplate {

	private final EmaySmsProperties emaySmsProperties;

	/**
	 * 发起短信请求
	 */
	private void request(Map<String, String> params, String path) {
		// 应用ID
		String appId = emaySmsProperties.getAppId();
		params.put("appId", appId);
		// 时间戳
		String timestamp = DateUtil.toString(new Date(), "yyyyMMddHHmmss");
		// 签名
		String sign = Md5.md5((appId + emaySmsProperties.getSecretKey() + timestamp).getBytes());
		params.put("sign", sign);
		params.put("timestamp", timestamp);
//		String json = request(params, emaySmsProperties.getHost() + path);
		/*if (json != null) {
			ResponseData<SmsResponse[]> data = JsonHelper.fromJson(new TypeToken<ResponseData<SmsResponse[]>>() {
			}, json);
			String code = data.getCode();
			if ("SUCCESS".equals(code)) {
				for (SmsResponse d : data.getData()) {
					System.out.println("data:" + d.getMobile() + "," + d.getSmsId() + "," + d.getCustomSmsId());
				}
			}
		}*/
	}

	/**
	 * 短信平台
	 */
	@Override
	public String getSmsPlatform() {
		return EmayPlatform.EMAY;
	}

	/**
	 * 单条发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendSingleSmsResponse singleSend(SendSingleSmsRequest request) {
		return null;
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendBatchSmsResponse batchSend(SendBatchSmsRequest request) {
		return null;
	}

	/**
	 * 个性化内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendMultiSmsResponse multiSend(SendMultiSmsRequest request) {
		return null;
	}

	/**
	 * 模板发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendTemplateSmsResponse templateSend(SendTemplateSmsRequest request) {
		return null;
	}
}
