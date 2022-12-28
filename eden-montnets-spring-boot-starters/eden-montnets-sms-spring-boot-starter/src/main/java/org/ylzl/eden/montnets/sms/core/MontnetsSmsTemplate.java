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

package org.ylzl.eden.montnets.sms.core;

import com.google.common.collect.Lists;
import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.common.MultiMt;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.batch.BatchSendSmsResponse;
import org.ylzl.eden.dynamic.sms.SmsTemplate;
import org.ylzl.eden.dynamic.sms.model.Sms;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.multi.MultiSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsRequest;
import org.ylzl.eden.dynamic.sms.model.single.SingleSendSmsResponse;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsRequest;
import org.ylzl.eden.dynamic.sms.model.template.SendTemplateSmsResponse;
import org.ylzl.eden.dynamic.sms.SmsType;
import org.ylzl.eden.montnets.sms.config.MontnetsSmsConfig;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;
import org.ylzl.eden.spring.framework.error.util.AssertUtils;

import java.util.Collection;
import java.util.List;

/**
 * 梦网短信操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@RequiredArgsConstructor
@Slf4j
public class MontnetsSmsTemplate implements SmsTemplate, InitializingBean {

	private final MontnetsSmsConfig montnetsSmsConfig;

	private SmsSendConn smsSendConn;

	/**
	 * 短信类型
	 *
	 * @return 短信类型
	 */
	@Override
	public String smsType() {
		return SmsType.MONTNETS.name();
	}

	/**
	 * 初始化配置
	 */
	@Override
	public void afterPropertiesSet() {
		populateProperties();
		initAccountInfo();
		smsSendConn = new SmsSendConn(montnetsSmsConfig.isKeepAlive());
	}

	/**
	 * 填充属性
	 */
	private void populateProperties() {
		GlobalParams globalParams = GlobalParams.getInstance();
		globalParams.setRequestPath(montnetsSmsConfig.getGlobalParams().getRequestPath());
		globalParams.setNeedLog(montnetsSmsConfig.getGlobalParams().getNeedLog());
		globalParams.setPoolNumber(montnetsSmsConfig.getGlobalParams().getPoolNumber());
		globalParams.setPwdEncryptType(montnetsSmsConfig.getGlobalParams().getPwdEncryptType());

		globalParams.setMsgMtEncode(montnetsSmsConfig.getGlobalParams().getMsgMtEncode());
		globalParams.setMsgMtEncrypt(montnetsSmsConfig.getGlobalParams().getMsgMtEncrypt());
		globalParams.setMtKey(montnetsSmsConfig.getGlobalParams().getMtKey());
		globalParams.setMtFixedKey(montnetsSmsConfig.getGlobalParams().getMtFixedKey());

		// FIXME：globalParams.setMsgMoEncode，梦网没有设置这个入口
		globalParams.setMsgMoEncrypt(montnetsSmsConfig.getGlobalParams().getMsgMoEncrypt());
		globalParams.setMoKey(montnetsSmsConfig.getGlobalParams().getMoKey());
		globalParams.setMoFixedKey(montnetsSmsConfig.getGlobalParams().getMoFixedKey());
		smsSendConn = new SmsSendConn(true);
	}

	/**
	 * 初始化账号
	 */
	private void initAccountInfo() {
		AssertUtils.notEmpty(montnetsSmsConfig.getAccountInfo(), "请求梦网的域名不能为空，请联系梦网客服进行获取。");
		int size = montnetsSmsConfig.getAccountInfo().size();
		String address1 = montnetsSmsConfig.getAccountInfo().get(0);
		String address2 = size > 1? montnetsSmsConfig.getAccountInfo().get(1) : null;
		String address3 = size > 2? montnetsSmsConfig.getAccountInfo().get(2) : null;
		String address4 = size > 3? montnetsSmsConfig.getAccountInfo().get(3) : null;
		for (String accountInfo : montnetsSmsConfig.getAccountInfo()) {
			String[] split = accountInfo.split("@@");
			int result = ConfigManager.setAccountInfo(split[0], split[1], 1, address1, address2, address3, address4);
			// 判断返回结果（0：成功，1：失败）
			AssertUtils.state(result == 0, "设置用户账号信息失败，错误码：" + result);
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
		log.debug("发起梦网单条短信请求，参数：{}", request);
		Message message = new Message();
		message.setCustid(request.getCustomSmsId());
		message.setMobile(request.getPhoneNumber());
		message.setContent(request.getSmsContent());
		StringBuffer returnValue = new StringBuffer();
		try {
			smsSendConn.batchSend(message, returnValue);
			return SingleSendSmsResponse.builder()
				.success(true)
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
		log.debug("发起梦网相同内容群发请求，参数：{}", request);
		Message message = new Message();
		message.setCustid(request.getCustomSmsId());
		message.setMobile(String.join(",", request.getPhoneNumbers()));
		message.setContent(request.getSmsContent());
		StringBuffer returnValue = new StringBuffer();
		try {
			smsSendConn.batchSend(message, returnValue);
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
		log.debug("发起梦网个性化内容群发请求，参数：{}", request);
		Collection<Sms> smsList = request.getSmsList();
		List<MultiMt> multixMts = Lists.newArrayListWithCapacity(smsList.size());
		for (Sms model : smsList) {
			AssertUtils.notNull(model.getPhoneNumber(), "REQ-ERROR-400", "发送梦网短信的接收号码不能为空");
			MultiMt multixMt = new MultiMt();
			multixMt.setMobile(model.getPhoneNumber());
			multixMt.setContent(model.getSmsContent());
			multixMt.setCustid(model.getCustomSmsId());
			multixMts.add(multixMt);
		}
		Message message = new Message();
		message.setMultimt(multixMts.toString());
		StringBuffer returnValue = new StringBuffer();
		try {
			smsSendConn.batchSend(message, returnValue);
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
}
