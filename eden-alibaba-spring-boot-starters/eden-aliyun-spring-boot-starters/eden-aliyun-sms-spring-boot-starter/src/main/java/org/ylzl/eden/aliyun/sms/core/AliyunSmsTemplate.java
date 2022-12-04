package org.ylzl.eden.aliyun.sms.core;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyuncs.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.ylzl.eden.dynamic.sms.batch.BatchSendSmsRequest;
import org.ylzl.eden.dynamic.sms.batch.BatchSendSmsResponse;
import org.ylzl.eden.dynamic.sms.core.SmsTemplate;
import org.ylzl.eden.dynamic.sms.multi.MultiSendSmsRequest;
import org.ylzl.eden.dynamic.sms.multi.MultiSendSmsResponse;
import org.ylzl.eden.dynamic.sms.single.SingleSendSmsRequest;
import org.ylzl.eden.dynamic.sms.single.SingleSendSmsResponse;
import org.ylzl.eden.dynamic.sms.template.SendTemplateSmsRequest;
import org.ylzl.eden.dynamic.sms.template.SendTemplateSmsResponse;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;
import org.ylzl.eden.spring.framework.error.util.AssertUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 阿里云短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@RequiredArgsConstructor
@Slf4j
public class AliyunSmsTemplate implements SmsTemplate {

	private static final String OK = "ok";

	private final ISmsService smsService;

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

		Collection<String> phoneNumbers = request.getPhoneNumbers();
		AssertUtils.notNull(phoneNumbers,"REQ-ERROR-400", "发送阿里云短信的接收号码不能为空");
		sendSmsRequest.setPhoneNumbers(StringUtils.collectionToCommaDelimitedString(phoneNumbers));

		Map<String, String> templateParam = request.getTemplateParam();
		AssertUtils.notNull(templateParam,"REQ-ERROR-400", "发送阿里云短信的模板参数不能为空");
		sendSmsRequest.setTemplateParam(JSONUtil.toJsonStr(templateParam));
		sendSmsRequest.setTemplateCode(request.getTemplateCode());

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
			throw new ThirdServiceException("SMS-ERROR-500", e.getMessage());
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
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public BatchSendSmsResponse batchSend(BatchSendSmsRequest request) {
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}

	/**
	 * 个性化群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public MultiSendSmsResponse multiSend(MultiSendSmsRequest request) {
		throw new UnsupportedOperationException("阿里云暂时不支持自定义内容发送");
	}
}
