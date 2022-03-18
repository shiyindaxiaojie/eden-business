package org.ylzl.eden.spring.boot.vod.core;

import com.qcloud.cos.utils.Base64;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

/**
 * 腾讯云VOD 操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @link https://cloud.tencent.com/document/product/266/10276
 * @since 2021-06-17
 */
@Slf4j
public class VODTemplate implements InitializingBean {

	private final VODConfig vodConfig;
	@Getter
	private VodUploadClient vodUploadClient;

	public VODTemplate(VODConfig vodConfig) {
		this.vodConfig = vodConfig;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		vodUploadClient = new VodUploadClient(vodConfig.getSecretId(), vodConfig.getSecretKey());
	}

	/**
	 * 上传音频
	 *
	 * @param mediaFilePath 音频文件路径
	 * @return 返回消息体
	 * @throws Exception 上传异常
	 */
	public VodUploadResponse upload(String mediaFilePath) throws Exception {
		return this.upload(mediaFilePath, null);
	}

	/**
	 * 上传音频
	 *
	 * @param mediaFilePath 音频文件路径
	 * @param coverFilePath 封面文件路径
	 * @return 返回消息体
	 * @throws Exception 上传异常
	 */
	public VodUploadResponse upload(String mediaFilePath, String coverFilePath) throws Exception {
		VodUploadRequest request = new VodUploadRequest();
		// request.setConcurrentUploadNumber(5); // 指定分片并发数
		request.setMediaFilePath(mediaFilePath);
		if (StringUtils.isNotBlank(coverFilePath)) {
			request.setCoverFilePath(coverFilePath);
		}
		return vodUploadClient.upload(vodConfig.getRegion(), request);
	}

	/**
	 * 获取上传签名
	 *
	 * @return
	 * @throws Exception
	 */
	public String getUploadSignature() throws Exception {
		Signature sign = new Signature();
		sign.setSecretId(vodConfig.getSecretId());
		sign.setSecretKey(vodConfig.getSecretKey());
		sign.setCurrentTime(System.currentTimeMillis() / 1000);
		sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
		sign.setSignValidDuration(vodConfig.getShortignValidDuration());
		return sign.getUploadSignature();
	}

	@Data
	private static class Signature {

		private static final String HMAC_ALGORITHM = "HmacSHA1"; //签名算法
		private static final String CONTENT_CHARSET = "UTF-8";
		private String secretId;
		private String secretKey;
		private long currentTime;
		private int random;
		private int signValidDuration;

		public static byte[] byteMerger(byte[] byte1, byte[] byte2) {
			byte[] byte3 = new byte[byte1.length + byte2.length];
			System.arraycopy(byte1, 0, byte3, 0, byte1.length);
			System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
			return byte3;
		}

		/**
		 * 获取上传签名
		 *
		 * @return
		 * @throws Exception
		 */
		public String getUploadSignature() throws Exception {
			String strSign = "";
			String contextStr = "";
			// 生成原始参数字符串
			long endTime = (currentTime + signValidDuration);
			contextStr += "secretId=" + java.net.URLEncoder.encode(secretId, "utf8");
			contextStr += "&currentTimeStamp=" + currentTime;
			contextStr += "&expireTime=" + endTime;
			contextStr += "&random=" + random;
			try {
				Mac mac = Mac.getInstance(HMAC_ALGORITHM);
				SecretKeySpec secretKey = new SecretKeySpec(this.secretKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
				mac.init(secretKey);
				byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
				byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
				strSign = base64Encode(sigBuf);
				strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
			} catch (Exception e) {
				throw e;
			}
			return strSign;
		}

		private String base64Encode(byte[] buffer) {
			return Base64.encodeAsString(buffer);
		}
	}
}
