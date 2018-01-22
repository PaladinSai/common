package com.ming.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ming.common.constant.config.Config;

/**
 * 类名：WxpayUtil<br>
 * 功能：微信支付必备工具包。<br>
 * 版本：1.2<br>
 * 日期：2015-11-02<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class WechatPayUtil {
	
	/** 公众账号ID */
	//private static String WECHAT_APPID = "";
	/** 商户号 */
	//private static String WECHAT_MCH_ID = "";
	/** 异步通知地址（接收微信支付系统返回的异步通知信息） */
	//private static String WECHAT_NOTIFY_URL = "";
	/** API秘钥的值 */
	private static String WECHAT_PARTNERKEY = "";
	/** 证书地址 */
	//private static String WECHAT_APICLIENT_CERTLOCATION = "";
	// ---------------------------------------------------------------

	/**
	 * 方法名：createSign<br>
	 * 功能：生成MD5加密的sign<br>
	 * 说明：由a-z排列参数，最后加上key。进行md5加密得到sign
	 * 
	 * @param params
	 *            需上传的参数
	 * @return MD5加密后的sign
	 */
	public static String createSign(SortedMap<String, String> params) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = params.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (v != null && !v.equals("") && !k.equals("sign") && !k.equals("key")) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WECHAT_PARTNERKEY);
		String sign = MD5.md5Encode(sb.toString(), Config.CHAR_SET).toUpperCase();
		return sign;
	}

	public static String createSign(Map<String, String> map) {
		SortedMap<String, String> params = mapToSortedMap(map);
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = params.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (v != null && !v.equals("") && !k.equals("sign") && !k.equals("key")) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WECHAT_PARTNERKEY);
		String sign = MD5.md5Encode(sb.toString(), Config.CHAR_SET).toUpperCase();
		return sign;
	}

	/**
	 * 判断是否是财付通签名
	 * 
	 * @param params
	 * @param sign
	 * @return
	 */
	public static boolean isTenpaySign(Map<String, String> params, String sign) {
		// 将获取的参数排序
		/*
		 * SortedMap<String,String> signMap = new TreeMap<String,String>(); Set
		 * es = params.entrySet(); Iterator it = es.iterator();
		 * while(it.hasNext()){ Map.Entry entry = (Map.Entry) it.next(); String
		 * k = (String)entry.getKey(); String v = (String)entry.getValue();
		 * signMap.put(k, v); }
		 */
		// 将Map排序为SortedMap params
		SortedMap<String, String> signMap = mapToSortedMap(params);
		// 将获取的参数生成sign比较值mysign
		String mysign = createSign(signMap);
		// 比较获取的sign和singStr是否相同
		return mysign.equals(sign);
	}

	/**
	 * 将Map转换为SortedMap<String,String>格式
	 * 
	 * @param Map
	 *            map
	 * @return SortedMap<String,String>
	 */
	@SuppressWarnings("rawtypes")
	public static SortedMap<String, String> mapToSortedMap(Map map) {
		SortedMap<String, String> sortedMap = new TreeMap<String, String>();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
						// k = new String(k.getBytes("ISO-8859-1"),"UTF-8");
						// v = new String(v.getBytes("ISO-8859-1"), "UTF-8");
			sortedMap.put(k, v);
		}
		return sortedMap;
	}

	/**
	 * 微信支付返回成功
	 * @return
	 */
	public static String resultSuccess() {
		SortedMap<String, String> successMap = new TreeMap<String, String>();
		successMap.put("return_code", "SUCCESS");
		return XMLUtil.setXML(successMap);
	}
	
	/**
	 * 微信支付返回失败
	 * @param msg
	 * @return
	 */
	public static String resultFail(String msg) {
		SortedMap<String, String> failMap = new TreeMap<String, String>();
		failMap.put("return_code", "FAIL");
		failMap.put("return_msg", msg);
		return XMLUtil.setXML(failMap);
	}
	
}
