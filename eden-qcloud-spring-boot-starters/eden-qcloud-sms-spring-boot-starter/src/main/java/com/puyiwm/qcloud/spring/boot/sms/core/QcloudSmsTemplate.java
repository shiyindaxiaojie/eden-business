package com.puyiwm.qcloud.spring.boot.sms.core;

import com.puyiwm.qcloud.spring.boot.sms.config.QcloudSmsConfig;
import com.puyiwm.spring.framework.error.ClientErrorType;
import com.puyiwm.spring.framework.error.ThirdServiceException;
import com.puyiwm.spring.integration.sms.common.SmsPlatform;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.Collection;
import java.util.Map;

/**
 * 腾讯云短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@RequiredArgsConstructor
@Slf4j
public class QcloudSmsTemplate implements SmsTemplate, InitializingBean {

	private final QcloudSmsConfig qcloudSmsConfig;

	private SmsClient smsClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		Credential credential = new Credential(qcloudSmsConfig.getAccessKey(), qcloudSmsConfig.getSecretKey());
		smsClient = new SmsClient(credential, qcloudSmsConfig.getRegion());
	}

	/**
	 * 短信平台
	 */
	@Override
	public String getSmsPlatform() {
		return SmsPlatform.QCLOUD;
	}

	/**
	 * 模板发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendTemplateSmsResponse templateSend(SendTemplateSmsRequest request) {
		log.debug("发送腾讯云短信请求，参数：{}", request);
		com.tencentcloudapi.sms.v20210111.models.SendSmsRequest sendSmsRequest =
			new com.tencentcloudapi.sms.v20210111.models.SendSmsRequest();

		SendTemplateSmsRequest.TemplateSms templateSms = request.getTemplateSms();
		ClientErrorType.notNull(templateSms, "400", "发送腾讯云短信的模板对象不能为空");

		Collection<String> phoneNumbers = templateSms.getPhoneNumbers();
		ClientErrorType.notNull(phoneNumbers,"400", "发送腾讯云短信的接收号码不能为空");
		sendSmsRequest.setPhoneNumberSet(phoneNumbers.toArray(new String[0]));

		Map<String, String> templateParam = templateSms.getTemplateParam();
		ClientErrorType.notNull(templateParam,"400", "发送腾讯云短信的模板参数不能为空");
		sendSmsRequest.setTemplateParamSet(templateParam.keySet().toArray(new String[0]));
		sendSmsRequest.setTemplateId(templateSms.getTemplateCode());

		request.setSignName(request.getSignName());
		sendSmsRequest.setSmsSdkAppId(qcloudSmsConfig.getSmsSdkAppId());
		try {
			smsClient.SendSms(sendSmsRequest);
			return SendTemplateSmsResponse.builder()
				.success(true)
				.build();
		} catch (TencentCloudSDKException e) {
			log.error("发送腾讯云短信失败，异常：{}", e.getMessage(), e);
			throw new ThirdServiceException("500", e.getMessage());
		}
	}


	/**
	 * 单条发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendSingleSmsResponse singleSend(SendSingleSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendBatchSmsResponse batchSend(SendBatchSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}

	/**
	 * 个性化群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendMultiSmsResponse multiSend(SendMultiSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}
}
