package org.ylzl.eden.spring.boot.montnets.sms.core;

import com.google.common.collect.Lists;
import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.common.MultiMt;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.ylzl.eden.common.sms.core.SmsModel;
import org.ylzl.eden.common.sms.core.SmsTemplate;
import org.ylzl.eden.common.sms.core.batch.BatchSendSmsRequest;
import org.ylzl.eden.common.sms.core.batch.BatchSendSmsResponse;
import org.ylzl.eden.common.sms.core.multi.MultiSendSmsRequest;
import org.ylzl.eden.common.sms.core.multi.MultiSendSmsResponse;
import org.ylzl.eden.common.sms.core.single.SingleSendSmsRequest;
import org.ylzl.eden.common.sms.core.single.SingleSendSmsResponse;
import org.ylzl.eden.common.sms.core.template.SendTemplateSmsRequest;
import org.ylzl.eden.common.sms.core.template.SendTemplateSmsResponse;
import org.ylzl.eden.spring.boot.montnets.sms.env.MontnetsSmsProperties;
import org.ylzl.eden.spring.framework.error.ClientAssert;
import org.ylzl.eden.spring.framework.error.ThirdServiceException;
import org.ylzl.eden.spring.framework.error.util.AssertEnhancer;

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

	private final MontnetsSmsProperties montnetsSmsProperties;

	private SmsSendConn smsSendConn;

	@Override
	public void afterPropertiesSet() throws Exception {
		populateProperties();
		initAccountInfo();
		smsSendConn = new SmsSendConn(montnetsSmsProperties.getSms().isKeepAlive());
	}

	/**
	 * 填充属性
	 */
	private void populateProperties() {
		GlobalParams globalParams = GlobalParams.getInstance();
		globalParams.setRequestPath(montnetsSmsProperties.getSms().getGlobalParams().getRequestPath());
		globalParams.setNeedLog(montnetsSmsProperties.getSms().getGlobalParams().getNeedLog());
		globalParams.setPoolNumber(montnetsSmsProperties.getSms().getGlobalParams().getPoolNumber());
		globalParams.setPwdEncryptType(montnetsSmsProperties.getSms().getGlobalParams().getPwdEncryptType());

		globalParams.setMsgMtEncode(montnetsSmsProperties.getSms().getGlobalParams().getMsgMtEncode());
		globalParams.setMsgMtEncrypt(montnetsSmsProperties.getSms().getGlobalParams().getMsgMtEncrypt());
		globalParams.setMtKey(montnetsSmsProperties.getSms().getGlobalParams().getMtKey());
		globalParams.setMtFixedKey(montnetsSmsProperties.getSms().getGlobalParams().getMtFixedKey());

		// FIXME：globalParams.setMsgMoEncode，梦网没有设置这个入口
		globalParams.setMsgMoEncrypt(montnetsSmsProperties.getSms().getGlobalParams().getMsgMoEncrypt());
		globalParams.setMoKey(montnetsSmsProperties.getSms().getGlobalParams().getMoKey());
		globalParams.setMoFixedKey(montnetsSmsProperties.getSms().getGlobalParams().getMoFixedKey());
		smsSendConn = new SmsSendConn(true);
	}

	/**
	 * 初始化账号
	 */
	private void initAccountInfo() {
		AssertEnhancer.notEmpty(montnetsSmsProperties.getSms().getAccountInfo(), "请求梦网的域名不能为空，请联系联系梦网客服进行获取。");
		int size = montnetsSmsProperties.getSms().getAccountInfo().size();
		String address1 = montnetsSmsProperties.getSms().getAccountInfo().get(0);
		String address2 = size > 1? montnetsSmsProperties.getSms().getAccountInfo().get(1) : null;
		String address3 = size > 2? montnetsSmsProperties.getSms().getAccountInfo().get(2) : null;
		String address4 = size > 3? montnetsSmsProperties.getSms().getAccountInfo().get(3) : null;
		for (String accountInfo : montnetsSmsProperties.getSms().getAccountInfo()) {
			String[] split = accountInfo.split("@@");
			int result = ConfigManager.setAccountInfo(split[0], split[1], 1, address1, address2, address3, address4);
			// 判断返回结果（0：成功，1：失败）
			AssertEnhancer.state(result == 0, "设置用户账号信息失败，错误码：" + result);
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
			throw new ThirdServiceException("C0501", e.getMessage());
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
			throw new ThirdServiceException("C0501", e.getMessage());
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
		Collection<SmsModel> smsModelList = request.getSmsModelList();
		List<MultiMt> multixMts = Lists.newArrayListWithCapacity(smsModelList.size());
		for (SmsModel model : smsModelList) {
			ClientAssert.notNull(model.getPhoneNumber(), "A0001", "发送梦网短信的接收号码不能为空");
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
			throw new ThirdServiceException("C0501", e.getMessage());
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
