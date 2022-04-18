package org.ylzl.aliyun.spring.boot.sms.core;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.ylzl.eden.spring.framework.error.ClientErrorType;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;

import java.util.Collection;
import java.util.Map;

/**
 * 阿里云短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@RequiredArgsConstructor
@Slf4j
public class AliyunSmsTemplate implements SmsTemplate {

	private static final String OK = "ok";

	private final ISmsService smsService;

	/**
	 * 短信平台
	 */
	@Override
	public String getSmsPlatform() {
		return SmsPlatform.ALIYUN;
	}

	/**
	 * 模板发送
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendTemplateSmsResponse templateSend(SendTemplateSmsRequest request) {
		log.debug("发送阿里云短信请求，参数：{}", request);
		com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest sendSmsRequest =
			new com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest();

		SendTemplateSmsRequest.TemplateSms templateSms = request.getTemplateSms();
		ClientErrorType.notNull(templateSms, "400", "发送阿里云短信的模板对象不能为空");

		Collection<String> phoneNumbers = templateSms.getPhoneNumbers();
		ClientErrorType.notNull(phoneNumbers,"400", "发送阿里云短信的接收号码不能为空");
		sendSmsRequest.setPhoneNumbers(StringUtils.collectionToCommaDelimitedString(phoneNumbers));

		Map<String, String> templateParam = templateSms.getTemplateParam();
		ClientErrorType.notNull(templateParam,"400", "发送阿里云短信的模板参数不能为空");
		sendSmsRequest.setTemplateParam(JSONUtil.toJsonStr(templateParam));
		sendSmsRequest.setTemplateCode(templateSms.getTemplateCode());

		sendSmsRequest.setSignName(request.getSignName());
		try {
			com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse response =
				smsService.sendSmsRequest(sendSmsRequest);
			return SendTemplateSmsResponse.builder()
				.success(OK.equalsIgnoreCase(response.getCode()))
				.errCode(response.getCode())
				.errMessage(response.getMessage())
				.build();
		} catch (ClientException e) {
			log.error("发送阿里云短信失败，异常：{}", e.getMessage(), e);
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
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendBatchSmsResponse batchSend(SendBatchSmsRequest request) {
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}

	/**
	 * 个性化群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public SendMultiSmsResponse multiSend(SendMultiSmsRequest request) {
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}
}
