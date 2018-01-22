package com.ming.common.util.bussiness;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ming.common.util.common.XMLUtil;
import com.ming.common.util.http.HttpClientUtil;

/**
 * 类名：SMSUtil<br>
 * 功能：给用户发送短消息<br>
 * 版本：1.0<br>
 * 日期：2017-01-20<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class SMSUtil {

	private Logger logger = LogManager.getLogger(SMSUtil.class.getName());

	private static final SMSUtil INSTANCE = new SMSUtil();

	public static SMSUtil getInstance() {
		return INSTANCE;
	}

	/** 短信平台用户名 */
	private static String USERNAME = "zxkj";
	/** 短信平台密码 */
	private static String USERPWD = "100587";
	/** 短信平台成功代码 */
	private static String SUCCESS_CODE = "1";

	public boolean sendMsg(String phone, String content) throws UnsupportedEncodingException {
		String url = "http://sms.ue35.net/sms/interface/sendmess.htm?username=" + USERNAME + "&userpwd=" + USERPWD
				+ "&mobiles=" + phone + "&content=" + URLEncoder.encode(content, "utf-8") + "&mobilecount=1";
		logger.debug("提交的url为：" + url);
		String xml = HttpClientUtil.getInstance().sendHttpPost(url);
		logger.debug("返回的信息为：" + xml);
		Map<String, String> result = XMLUtil.getXML(xml);
		if (SUCCESS_CODE.equals(result.get("errorcode"))) {
			return true;
		} else {
			logger.error("发送短信失败，错误码：" + result.get("errorcode") + "，短信id：" + result.get("SMSID"));
			return false;
		}
	}

}
