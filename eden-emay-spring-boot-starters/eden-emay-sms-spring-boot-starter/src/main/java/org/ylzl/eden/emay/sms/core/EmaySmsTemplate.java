package org.ylzl.eden.emay.sms.core;

import cn.emay.sdk.client.SmsSDKClient;
import cn.emay.sdk.core.dto.sms.common.CustomSmsIdAndMobile;
import cn.emay.sdk.core.dto.sms.common.PersonalityParams;
import cn.emay.sdk.core.dto.sms.common.ResultModel;
import cn.emay.sdk.core.dto.sms.request.SmsBatchRequest;
import cn.emay.sdk.core.dto.sms.request.SmsPersonalityAllRequest;
import cn.emay.sdk.core.dto.sms.request.SmsSingleRequest;
import cn.emay.sdk.core.dto.sms.response.SmsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.ylzl.eden.dynamic.sms.model.Sms;
import org.ylzl.eden.dynamic.sms.core.SmsTemplate;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsRequest;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsResponse;
import org.ylzl.eden.emay.sms.config.EmaySmsConfig;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;

/**
 * 亿美短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@RequiredArgsConstructor
@Slf4j
public class EmaySmsTemplate implements SmsTemplate, InitializingBean {

	public static final String SUCCESS = "SUCCESS";

	private final EmaySmsConfig emaySmsConfig;

	private SmsSDKClient smsSDKClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isNotEmpty(emaySmsConfig.getIp()) && emaySmsConfig.getPort() > 0) {
			smsSDKClient = new SmsSDKClient(emaySmsConfig.getIp(), emaySmsConfig.getPort(),
				emaySmsConfig.getAppId(), emaySmsConfig.getSecretKey());
		} else {
			smsSDKClient = new SmsSDKClient(emaySmsConfig.getAppId(), emaySmsConfig.getSecretKey());
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
		log.debug("发起亿美单条短信请求，参数：{}", request);
		SmsSingleRequest smsSingleRequest = new SmsSingleRequest();
		smsSingleRequest.setCustomSmsId(request.getCustomSmsId());
		smsSingleRequest.setMobile(request.getPhoneNumber());
		smsSingleRequest.setContent(request.getSmsContent());
		try {
			ResultModel<SmsResponse> resultModel = smsSDKClient.sendSingleSms(smsSingleRequest);
			boolean isSuccess = isSuccess(resultModel);
			if (!isSuccess) {
				log.warn("发起梦网单条短信请求失败，错误代码：{}", resultModel.getCode());
				return SingleSendSmsResponse.builder()
					.success(false)
					.errCode(resultModel.getCode())
					.build();
			}
			log.info("发起亿美单条短信请求成功，参数：{}", request);
			return SingleSendSmsResponse.builder()
				.success(true)
				.smsId(resultModel.getResult().getSmsId())
				.build();
		} catch (Exception e) {
			log.error("发起梦网单条短信请求失败，异常：{}", e.getMessage(), e);
			throw new ThirdServiceException("SMS-SEND-500", e.getMessage());
		}
	}

	/**
	 * 相同内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public BatchSendSmsResponse batchSend(BatchSendSmsRequest request) {
		log.debug("发起亿美相同内容群发请求，参数：{}", request);
		SmsBatchRequest smsBatchRequest = new SmsBatchRequest();

		int size = request.getPhoneNumbers().size();
		CustomSmsIdAndMobile[] customSmsIdAndMobiles = new CustomSmsIdAndMobile[size];
		for (int i = 0; i < size; i++) {
			customSmsIdAndMobiles[i] = new CustomSmsIdAndMobile(request.getCustomSmsId(), request.getPhoneNumbers().get(i));
		}
		smsBatchRequest.setSmses(customSmsIdAndMobiles);
		smsBatchRequest.setContent(request.getSmsContent());
		try {
			ResultModel<SmsResponse[]> resultModel = smsSDKClient.sendBatchSms(smsBatchRequest);
			boolean isSuccess = isSuccess(resultModel);
			if (!isSuccess) {
				log.warn("发起亿美相同内容群发请求失败，错误代码：{}", resultModel.getCode());
				return BatchSendSmsResponse.builder()
					.success(false)
					.errCode(resultModel.getCode())
					.build();
			}
			log.info("发起亿美相同内容群发请求成功，参数：{}", request);
			return BatchSendSmsResponse.builder()
				.success(true)
				.build();
		} catch (Exception e) {
			log.error("发起梦网相同内容群发请求失败，异常：{}", e.getMessage(), e);
			throw new ThirdServiceException("SMS-SEND-500", e.getMessage());
		}
	}

	/**
	 * 个性化内容群发
	 *
	 * @param request 发送短信请求
	 * @return 发送短信响应
	 */
	@Override
	public MultiSendSmsResponse multiSend(MultiSendSmsRequest request) {
		log.debug("发起亿美个性化内容群发请求，参数：{}", request);
		SmsPersonalityAllRequest smsPersonalityAllRequest = new SmsPersonalityAllRequest();

		int size = request.getSmsList().size();
		PersonalityParams[] personalityParams = new PersonalityParams[size];
		for (int i = 0; i < size; i++) {
			Sms sms = request.getSmsList().get(i);
			personalityParams[i] = new PersonalityParams(sms.getCustomSmsId(),
				sms.getPhoneNumber(), sms.getSmsContent(), sms.getExtendedCode(), sms.getTimerTime());
		}
		smsPersonalityAllRequest.setSmses(personalityParams);
		try {
			ResultModel<SmsResponse[]> resultModel = smsSDKClient.sendPersonalityAllSMS(smsPersonalityAllRequest);
			boolean isSuccess = isSuccess(resultModel);
			if (!isSuccess) {
				log.warn("发起亿美个性化内容群发失败，错误代码：{}", resultModel.getCode());
				return MultiSendSmsResponse.builder()
					.success(false)
					.errCode(resultModel.getCode())
					.build();
			}
			log.info("发起亿美个性化内容群发请求成功，参数：{}", request);
			return MultiSendSmsResponse.builder()
				.success(true)
				.build();
		} catch (Exception e) {
			log.error("发起梦网个性化内容群发请求失败，异常：{}", e.getMessage(), e);
			throw new ThirdServiceException("SMS-SEND-500", e.getMessage());
		}
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

	private boolean isSuccess(ResultModel<?> resultModel) {
		return SUCCESS.equalsIgnoreCase(resultModel.getCode());
	}
}
