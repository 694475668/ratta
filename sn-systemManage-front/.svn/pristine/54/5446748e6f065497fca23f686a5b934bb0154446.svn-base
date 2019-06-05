package com.ratta.spnote.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ucloud.ufile.DownloadUrl;
import cn.ucloud.ufile.UFileClient;
import cn.ucloud.ufile.UFileConfig;
import cn.ucloud.ufile.UFileRequest;
import cn.ucloud.ufile.UFileResponse;
import cn.ucloud.ufile.sender.DeleteSender;
import cn.ucloud.ufile.sender.GetSender;
import cn.ucloud.ufile.sender.HeadSender;
import cn.ucloud.ufile.sender.PostSender;
import cn.ucloud.ufile.sender.PutSender;

/**
 * @author page ucloud相关文件操作方法 2018-10-31
 */
public class UFile {

	private static Logger logger = LoggerFactory.getLogger(UFile.class);

	public static String UCloudPublicKey = ConfigUtil.get("UCloudPublicKey");
	public static String UCloudPrivateKey = ConfigUtil.get("UCloudPrivateKey");
	public static String ProxySuffix = ConfigUtil.get("ProxySuffix");
	public static String DownloadProxySuffix = ConfigUtil.get("DownloadProxySuffix");
	public static String DownloadProxySuffix2 = ConfigUtil.get("DownloadProxySuffix2");

	/**
	 * 以put的形式上传文件
	 * 
	 * @param bucketName 为ufile分配的空间名
	 * @param key        ufile某个空间内的唯一标志
	 * @param filePath   本地文件路径
	 * @return
	 */
	public static boolean putFile(String bucketName, String key, String filePath) {
		boolean flag = false;

		UFileRequest request = new UFileRequest();
		// 为ufile分配的空间名
		request.setBucketName(bucketName);
		// ufile某个空间内的唯一标志
		request.setKey(key);
		// 本地文件路径
		request.setFilePath(filePath);

		UFileClient ufileClient = null;
		try {
			UFileConfig uFileConfig = new UFileConfig();
			uFileConfig.setUcloudPublicKey(UCloudPublicKey);
			uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
			uFileConfig.setProxySuffix(ProxySuffix);
			uFileConfig.setDownloadProxySuffix(DownloadProxySuffix);
			ufileClient = new UFileClient(uFileConfig);
			PutSender sender = new PutSender();
			sender.makeAuth(ufileClient, request);
			UFileResponse response = sender.send(ufileClient, request);
			if (response != null) {
				StatusLine StatusLine = response.getStatusLine();
				logger.info("status line:{}", response.getStatusLine());
				if (StatusLine.getStatusCode() == 200) {
					flag = true;
				}
			}
		} finally {
			ufileClient.shutdown();
		}
		return flag;
	}

	/**
	 * 以post的形式上传文件
	 * 
	 * @param bucketName 为ufile分配的空间名
	 * @param key        ufile某个空间内的唯一标志
	 * @param filePath   本地文件路径
	 * @return
	 */
	public static boolean postFile(String bucketName, String key, String filePath) {
		boolean flag = false;

		UFileRequest request = new UFileRequest();
		// 为ufile分配的空间名
		request.setBucketName(bucketName);
		// ufile某个空间内的唯一标志
		request.setKey(key);
		// 本地文件路径
		request.setFilePath(filePath);

		UFileClient ufileClient = null;
		try {
			UFileConfig uFileConfig = new UFileConfig();
			uFileConfig.setUcloudPublicKey(UCloudPublicKey);
			uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
			uFileConfig.setProxySuffix(ProxySuffix);
			uFileConfig.setDownloadProxySuffix(DownloadProxySuffix);
			ufileClient = new UFileClient(uFileConfig);
			PostSender sender = new PostSender();
			sender.makeAuth(ufileClient, request);
			UFileResponse response = sender.send(ufileClient, request);
			if (response != null) {
				StatusLine StatusLine = response.getStatusLine();
				logger.info("status line:{}", response.getStatusLine());
				if (StatusLine.getStatusCode() == 200) {
					flag = true;
				}
			}
		} finally {
			ufileClient.shutdown();
		}
		return flag;
	}

	/**
	 * 下载文件
	 * 
	 * @param bucketName 为ufile分配的空间名
	 * @param key        ufile某个空间内的唯一标志
	 * @return
	 */
	public static InputStream downloadFile(String bucketName, String key) {

		InputStream ins = null;
		UFileRequest request = new UFileRequest();
		request.setBucketName(bucketName);
		request.setKey(key);

		UFileClient ufileClient = null;
		try {
			UFileConfig uFileConfig = new UFileConfig();
			uFileConfig.setUcloudPublicKey(UCloudPublicKey);
			uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
			uFileConfig.setProxySuffix(ProxySuffix);
			uFileConfig.setDownloadProxySuffix(DownloadProxySuffix);
			ufileClient = new UFileClient(uFileConfig);
			GetSender sender = new GetSender();
			sender.makeAuth(ufileClient, request);
			UFileResponse response = sender.send(ufileClient, request);

			// 如果下载文件失败
			if (response.getStatusLine().getStatusCode() != 200 && response.getContent() != null) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(response.getContent()));
					String input;
					while ((input = br.readLine()) != null) {
						logger.error("下载文件失败{}", input);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (response.getContent() != null) {
						try {
							response.getContent().close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				ins = response.getContent();
			}
		} finally {
		}
		return ins;
	}

	/**
	 * 获取文件下载地址
	 * 
	 * @param bucketName 为ufile分配的空间名
	 * @param key        ufile某个空间内的唯一标志
	 * @param isPrivate  私有标志
	 * @param ttl        链接有效时间（单位为秒）
	 * @return
	 */
	public static String getdownloadUrl(String bucketName, String key, boolean isPrivate, int ttl) {

		UFileRequest request = new UFileRequest();
		request.setBucketName(bucketName);
		request.setKey(key);

		UFileConfig uFileConfig = new UFileConfig();
		uFileConfig.setUcloudPublicKey(UCloudPublicKey);
		uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
		uFileConfig.setProxySuffix(ProxySuffix);
		uFileConfig.setDownloadProxySuffix(DownloadProxySuffix2);
		UFileClient ufileClient = new UFileClient(uFileConfig);
		/*
		 * 针对私有的Bucket，为了防止盗链，建议生成的链接的有效期为ttl > 0; 如果 ttl == 0, 那么生成的链接永久有效
		 */
		DownloadUrl downloadUrl = new DownloadUrl();
		return downloadUrl.getUrl(ufileClient, request, ttl, isPrivate);
	}

	/**
	 * 删除文件
	 * 
	 * @param bucketName 为ufile分配的空间名
	 * @param key        ufile某个空间内的唯一标志
	 */
	public static boolean deleteFile(String bucketName, String key) {

		boolean flag = false;

		UFileRequest request = new UFileRequest();
		// 为ufile分配的空间名
		request.setBucketName(bucketName);
		// ufile某个空间内的唯一标志
		request.setKey(key);

		UFileClient ufileClient = null;
		try {
			UFileConfig uFileConfig = new UFileConfig();
			uFileConfig.setUcloudPublicKey(UCloudPublicKey);
			uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
			uFileConfig.setProxySuffix(ProxySuffix);
			uFileConfig.setDownloadProxySuffix(DownloadProxySuffix);
			ufileClient = new UFileClient(uFileConfig);
			DeleteSender sender = new DeleteSender();
			sender.makeAuth(ufileClient, request);
			UFileResponse response = sender.send(ufileClient, request);
			if (response != null) {
				StatusLine StatusLine = response.getStatusLine();
				logger.info("status line:{}", response.getStatusLine());
				if (StatusLine.getStatusCode() == 204) {
					flag = true;
				}
			}
		} finally {
			ufileClient.shutdown();
		}
		return flag;
	}

	/**
	 * 获取文件信息
	 * 
	 * @param bucketName
	 * @param key
	 * @return
	 */
	public static boolean getFile(String bucketName, String key) {
		boolean flag = false;

		UFileRequest request = new UFileRequest();
		// 为ufile分配的空间名
		request.setBucketName(bucketName);
		// ufile某个空间内的唯一标志
		request.setKey(key);

		UFileClient ufileClient = null;
		try {
			UFileConfig uFileConfig = new UFileConfig();
			uFileConfig.setUcloudPublicKey(UCloudPublicKey);
			uFileConfig.setUcloudPrivateKey(UCloudPrivateKey);
			uFileConfig.setProxySuffix(ProxySuffix);
			uFileConfig.setDownloadProxySuffix(DownloadProxySuffix);
			ufileClient = new UFileClient(uFileConfig);
			HeadSender sender = new HeadSender();
			sender.makeAuth(ufileClient, request);
			UFileResponse response = sender.send(ufileClient, request);
			if (response != null) {
				StatusLine StatusLine = response.getStatusLine();
				logger.info("status line:{}", response.getStatusLine());
				if (StatusLine.getStatusCode() == 200) {
					flag = true;
				}
			}
		} finally {
			ufileClient.shutdown();
		}
		return flag;
	}
}
