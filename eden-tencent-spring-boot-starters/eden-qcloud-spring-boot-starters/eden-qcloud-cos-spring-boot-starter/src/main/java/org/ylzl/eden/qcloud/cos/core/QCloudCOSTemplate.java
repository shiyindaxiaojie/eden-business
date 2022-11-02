package org.ylzl.eden.qcloud.cos.core;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.ylzl.eden.commons.collections.CollectionUtils;
import org.ylzl.eden.commons.lang.StringUtils;
import org.ylzl.eden.qcloud.cos.config.QCloudCOSConfig;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * 腾讯云COS 操作模板
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @link https://cloud.tencent.com/document/product/436/10199
 * @since 2021-12-23
 */
@RequiredArgsConstructor
@Slf4j
public class QCloudCOSTemplate implements InitializingBean, DisposableBean {

	private static final String COS_OBJECT_ACCESS_FORMAT = "http://{0}.cos.{1}.myqcloud.com/{2}";

	private final QCloudCOSConfig qcloudCosConfig;

	@Getter
	private COSClient cosClient;

	/**
	 * 初始化 COSClient
	 * <br/>COSClient 是线程安全的类，允许多线程访问同一实例，内部维持了一个连接池，
	 * 请确保程序生命周期内实例只有一个，并在不再需要使用时，调用 shutdown 方法将其关闭
	 */
	@Override
	public void afterPropertiesSet() {
		COSCredentials cred =
			new BasicCOSCredentials(qcloudCosConfig.getSecretId(), qcloudCosConfig.getSecretKey());
		Region region = new Region(qcloudCosConfig.getRegion());
		ClientConfig clientConfig = new ClientConfig(region);
		clientConfig.setConnectionTimeout(10000);
		cosClient = new COSClient(cred, clientConfig);
	}

	/**
	 * 销毁 COSClient
	 */
	@Override
	public void destroy() {
		cosClient.shutdown();
	}

	/**
	 * 创建存储桶
	 *
	 * @param bucketName 存储桶名称
	 * @param cannedAccessControlList 访问权限
	 * @return
	 */
	public Bucket createBucket(String bucketName, CannedAccessControlList cannedAccessControlList) {
		CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
		// bucket 的权限有 Private(私有读写)、PublicRead（公有读私有写）、PublicReadWrite（公有读写）
		createBucketRequest.setCannedAcl(cannedAccessControlList);
		return cosClient.createBucket(createBucketRequest);
	}

	/**
	 * 创建存储桶
	 *
	 * @param bucketName 存储桶名称
	 * @return
	 */
	public Bucket createBucket(String bucketName) {
		return createBucket(bucketName, CannedAccessControlList.Private);
	}


	/**
	 * 如果存储桶不存在创建
	 *
	 * @param bucketName 存储桶名称
	 * @return
	 */
	public void createBucketIfNotExists(String bucketName) {
		List<Bucket> buckets = cosClient.listBuckets();
		if (!CollectionUtils.isEmpty(buckets)) {
			return;
		}
		// 暂时没有判断 location 属性
		if (buckets.stream().anyMatch(b -> b.getName().equals(bucketName))) {
			return;
		}
		createBucket(bucketName);
	}

	/**
	 * 上传对象（文件）
	 *
	 * @param key        对象键，指定文件上传到 COS 上的路径
	 * @param uploadfile 对象文件
	 * @return PutObjectResult
	 */
	public PutObjectResult putObject(String key, File uploadfile) {
		return putObject(qcloudCosConfig.getBucketName(), key, uploadfile);
	}

	/**
	 * 上传对象（文件）
	 *
	 * <ul>
	 *   examplebucket-1250000000.cos.ap-guangzhou.myqcloud.com/images/picture.jpg
	 *   <li>examplebucket-1250000000 就是 bucket
	 *   <li>cos.ap-guangzhou.myqcloud.com 是所属区域的域名
	 *   <li>images/picture.jpg 是对象键key
	 * </ul>
	 *
	 * @param bucketName 存储桶名称
	 * @param key        对象键，指定文件上传到 COS 上的路径
	 * @param uploadFile 对象文件
	 * @return PutObjectResult
	 */
	public PutObjectResult putObject(String bucketName, String key, File uploadFile) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, uploadFile);
		putObjectRequest.setStorageClass(StorageClass.Standard);
		return cosClient.putObject(putObjectRequest);
	}

	/**
	 * 删除对象（文件）
	 *
	 * @param key 对象键，指定文件上传到 COS 上的路径
	 */
	public void deleteObject(String key) {
		cosClient.deleteObject(qcloudCosConfig.getBucketName(), key);
	}

	/**
	 * 删除对象（文件）
	 *
	 * @param bucketName 存储桶名称
	 * @param key        对象键，指定文件上传到 COS 上的路径
	 */
	public void putObject(String bucketName, String key) {
		cosClient.deleteObject(bucketName, key);
	}

	/**
	 * 下载对象（大文件）到本地
	 *
	 * @param key          对象键，指定文件在 COS 上的路径
	 * @param downloadFile 指定下载路径的目标文件
	 * @return
	 */
	public ObjectMetadata getObject(String key, File downloadFile) {
		return this.getObject(qcloudCosConfig.getBucketName(), key, downloadFile);
	}

	/**
	 * 下载对象（大文件）到本地
	 *
	 * @param bucketName   存储桶名称
	 * @param key          对象键，指定文件在 COS 上的路径
	 * @param downloadFile 指定下载路径的目标文件
	 * @return
	 */
	public ObjectMetadata getObject(String bucketName, String key, File downloadFile) {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
		return cosClient.getObject(getObjectRequest, downloadFile);
	}

	/**
	 * 下载对象（小文件）到本地
	 *
	 * @param key             对象键，指定文件在 COS 上的路径
	 * @param useTrafficLimit 是否启用带宽限制
	 * @return 下载对象的 CRC64
	 */
	public String getObject(String key, boolean useTrafficLimit) throws IOException {
		return this.getObject(qcloudCosConfig.getBucketName(), key, useTrafficLimit);
	}

	/**
	 * 下载对象（小文件）到本地
	 *
	 * @param bucketName      存储桶名称
	 * @param key             对象键，指定文件在 COS 上的路径
	 * @param useTrafficLimit 是否启用带宽限制
	 * @return 下载对象的 CRC64
	 */
	public String getObject(String bucketName, String key, boolean useTrafficLimit) throws IOException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
		if (useTrafficLimit) {
			// 限流使用的单位是 bit/s, 这里设置下载带宽限制为 10MB/s
			getObjectRequest.setTrafficLimit(80 * 1024 * 1024);
		}
		COSObject cosObject = cosClient.getObject(getObjectRequest);
		try (COSObjectInputStream ignored = cosObject.getObjectContent();) {
			return cosObject.getObjectMetadata().getCrc64Ecma();
		}
	}

	/**
	 * 获取对象（文件）访问 URL
	 *
	 * @param key 对象键
	 * @return 对象（文件）访问 URL
	 */
	public String getObjectUrl(String key) {
		return this.getObjectUrl(qcloudCosConfig.getBucketName(), key);
	}

	/**
	 * 获取对象（文件）访问 URL
	 *
	 * @param bucketName 存储桶名称
	 * @param key        对象键
	 * @return 对象（文件）访问 URL
	 */
	public String getObjectUrl(String bucketName, String key) {
		return MessageFormat.format(COS_OBJECT_ACCESS_FORMAT, bucketName, qcloudCosConfig.getRegion(), key);
	}

	/**
	 * 获取对象（文件）访问基础 URL
	 *
	 * @return examplebucket-1250000000.cos.ap-guangzhou.myqcloud.com/
	 */
	public String getObjectBaseUrl() {
		return MessageFormat.format(
			COS_OBJECT_ACCESS_FORMAT,
			qcloudCosConfig.getBucketName(),
			qcloudCosConfig.getRegion(),
			StringUtils.EMPTY);
	}
}
