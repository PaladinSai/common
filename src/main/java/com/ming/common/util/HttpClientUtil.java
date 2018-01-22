package com.ming.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ming.common.constant.config.Config;

public class HttpClientUtil extends HttpClientBase {

	private static Logger logger = LogManager.getLogger(HttpClientUtil.class.getName());

	private HttpClientUtil() {
		super();
	}

	private static final HttpClientUtil INSTANCE = new HttpClientUtil();

	public static HttpClientUtil getInstance() {
		return INSTANCE;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public String sendHttpPost(String httpUrl) {
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送Post请求
	 * @param url 地址
	 * @param body 参数内容
	 * @param type 参数类型（json,form）
	 * @return
	 */
	public String sendHttpPost(String url, String body, String type) {
		HttpPost httpPost = new HttpPost(url);
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(body, Config.CHAR_SET);
			stringEntity.setContentType(type);
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			logger.error("HttpClient出错：", e);
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Config.CHAR_SET));
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpGet(httpGet);
	}

}
