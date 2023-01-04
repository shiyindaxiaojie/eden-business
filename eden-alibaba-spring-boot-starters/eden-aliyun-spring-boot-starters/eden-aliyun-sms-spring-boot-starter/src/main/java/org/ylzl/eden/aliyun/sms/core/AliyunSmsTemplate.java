/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.aliyun.sms.core;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyuncs.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.ylzl.eden.dynamic.sms.SmsTemplate;
import org.ylzl.eden.dynamic.sms.SmsType;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsRequest;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsResponse;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;
import org.ylzl.eden.spring.framework.error.util.AssertUtils;
import org.ylzl.eden.spring.framework.json.support.JSONHelper;

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
	 * 短信类型
	 *
	 * @return 短信类型
	 */
	@Override
	public String smsType() {
		return SmsType.ALIYUN.name();
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

		Collection<String> phoneNumbers = request.getPhoneNumbers();
		AssertUtils.notNull(phoneNumbers,"REQ-ERROR-400", "发送阿里云短信的接收号码不能为空");
		sendSmsRequest.setPhoneNumbers(StringUtils.collectionToCommaDelimitedString(phoneNumbers));

		Map<String, String> templateParam = request.getTemplateParam();
		AssertUtils.notNull(templateParam,"REQ-ERROR-400", "发送阿里云短信的模板参数不能为空");
		sendSmsRequest.setTemplateParam(JSONHelper.json().toJSONString(templateParam));
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
			throw new ThirdServiceException("SMS-SEND-500", e.getMessage());
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
