package com.ming.common.util.common;

/**
 * 类名：StringUtil<br>
 * 功能：字符串工具包<br>
 * 版本：1.0<br>
 * 日期：2017-01-13<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return null == str || str.trim().isEmpty();
	}

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str) {
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

}
