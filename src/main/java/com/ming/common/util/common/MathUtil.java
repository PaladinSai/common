package com.ming.common.util.common;

import java.security.SecureRandom;

/**
 * 类名：MathUtil<br>
 * 功能：获取随机数方法<br>
 * 版本：1.0<br>
 * 日期：2017-01-20<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class MathUtil {
	
	/**
	 * 获取整数类型随机数，传入几就获取几位（最大10位）
	 * @param i
	 * @return
	 */
	public static int getRandomInt(int i) {
		int nine = 9;
		int one = 1;
		for (int j = 1; j < i; j++) {
			nine *= 10;
			one *= 10;
		}
		return new SecureRandom().nextInt(nine) + one;
	}
}
