package com.ming.common.util.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 类名：ServletUtil<br>
 * 功能：Servlet工具包<br>
 * 版本：1.0<br>
 * 日期：2017-01-13<br>
 * 说明：暂无
 * @author Ming
 *
 */

public class ServletUtil {
	
	private static Logger logger = LogManager.getLogger(ServletUtil.class.getName());
	/**
	 * 根据URL获取要调用的类名（不带Action后缀）
	 * @param request
	 * @return
	 */
	public static String getClassName(HttpServletRequest request) {
		String qUri = request.getRequestURI();
		qUri = qUri.substring(request.getContextPath().length() + 1);
		int tempIndex2 = qUri.lastIndexOf("/");
		if (tempIndex2 > 0) {
			return qUri.substring(0, tempIndex2);
		}
		return null;
	}

	/**
	 * 根据URL获取要调用的方法名
	 * @param request
	 * @return
	 */
	public static String getMethodName(HttpServletRequest request) {
		String qUri = request.getRequestURI();
		qUri = qUri.substring(request.getContextPath().length() + 1);
		int tempIndex = qUri.lastIndexOf(".");
		if (tempIndex > 0) {
			int tempIndex2 = qUri.lastIndexOf("/");
			if (tempIndex2 > 0) {
				return qUri.substring(tempIndex2 + 1, tempIndex);
			}
		}
		return null;
	}
	
	/**
	 * 获取body中的json字符串
	 * @param request
	 * @return
	 */
	public static String getJSON(HttpServletRequest request) {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("读取JSON错误", e);
			return null;
		}
		
	}
}
