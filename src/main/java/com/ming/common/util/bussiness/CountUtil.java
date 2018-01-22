package com.ming.common.util.bussiness;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 类名：Count<br>
 * 功能：工具类。提供把金额转化为以分为单位的功能<br>
 * 版本：1.1<br>
 * 时间：2015-06-23<br>
 * 说明：1.1版本增加将分转化为元<br>
 * @author Ming
 *
 */
public class CountUtil {
	
	/**
	 * 将以元为单位的金额转化为以分为单位。
	 * @param String yuan 以元为单位的金额
	 * @return String 以分为单位的金额
	 */
	public static String yuanToCent(String yuan){
		return String.format("%.0f", Float.parseFloat(yuan) * 100);
	}
		
	/**
	 * 将以分为单位的金额转化为以元为单位
	 * @param String cent 以分为单位的金额
	 * @return String yuan 以元为单位的金额
	 */
	public static String centToYuan(String cent){
		float temp = Float.parseFloat(cent);
		temp = temp/100;
		String yuan = String.format("%.2f",temp);
		return yuan;
	}
	
	/**
	 * 保留小数点后两位（四舍五入）
	 * @param num
	 * @return
	 */
	public static BigDecimal format(BigDecimal num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new BigDecimal(df.format(num));
	}
}
