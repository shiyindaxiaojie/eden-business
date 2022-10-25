package org.ylzl.eden.spring.boot.qcloud.sms.core;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.ylzl.eden.common.sms.core.SmsTemplate;
import org.ylzl.eden.common.sms.core.batch.BatchSendSmsRequest;
import org.ylzl.eden.common.sms.core.batch.BatchSendSmsResponse;
import org.ylzl.eden.common.sms.core.multi.MultiSendSmsRequest;
import org.ylzl.eden.common.sms.core.multi.MultiSendSmsResponse;
import org.ylzl.eden.common.sms.core.single.SingleSendSmsRequest;
import org.ylzl.eden.common.sms.core.single.SingleSendSmsResponse;
import org.ylzl.eden.common.sms.core.template.SendTemplateSmsRequest;
import org.ylzl.eden.common.sms.core.template.SendTemplateSmsResponse;
import org.ylzl.eden.spring.boot.qcloud.sms.config.QcloudSmsConfig;
import org.ylzl.eden.spring.framework.error.ClientErrorType;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;

import java.util.Collection;
import java.util.Map;

/**
 * 腾讯云短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
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

		Collection<String> phoneNumbers = request.getPhoneNumbers();
		ClientErrorType.notNull(phoneNumbers,"A0001", "发送腾讯云短信的接收号码不能为空");
		sendSmsRequest.setPhoneNumberSet(phoneNumbers.toArray(new String[0]));

		Map<String, String> templateParam = request.getTemplateParam();
		ClientErrorType.notNull(templateParam,"A0001", "发送腾讯云短信的模板参数不能为空");
		sendSmsRequest.setTemplateParamSet(templateParam.keySet().toArray(new String[0]));
		sendSmsRequest.setTemplateId(request.getTemplateCode());

		request.setSignName(request.getSignName());
		sendSmsRequest.setSmsSdkAppId(qcloudSmsConfig.getSmsSdkAppId());
		try {
			smsClient.SendSms(sendSmsRequest);
			return SendTemplateSmsResponse.builder()
				.success(true)
				.build();
		} catch (TencentCloudSDKException e) {
			log.error("发送腾讯云短信失败，异常：{}", e.getMessage(), e);
			throw new ThirdServiceException("C0501", e.getMessage());
		}
	}


	/**
	 * 单条发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SingleSendSmsResponse singleSend(SingleSendSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public BatchSendSmsResponse batchSend(BatchSendSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}

	/**
	 * 个性化群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public MultiSendSmsResponse multiSend(MultiSendSmsRequest request) {
		throw new UnsupportedOperationException("腾讯云暂时不支持自定义内容发送");
	}
}
