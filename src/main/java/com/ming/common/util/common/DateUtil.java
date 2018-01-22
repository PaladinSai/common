package com.ming.common.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类名：DateUtil<br>
 * 功能：工具类，可用于获取系统时间日期。<br>
 * 版本：1.1<br>
 * 日期：2015-06-25<br>
 * 说明：增加时间格式互相转换方法
 * 
 * @author Ming
 *
 */
public class DateUtil {

	/** 年月日时分秒（无下划线） yyyyMMddHHmmss */
	public static final String DATE_TIME = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String DATE = "yyyyMMdd";

	/** 年月日 yyyy-MM-dd */
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME = "HH:mm:ss";
	/** 年月日时分秒毫秒yyyy-MM-dd HH:mm:ss.SSS格式 */
	public static final String DATE_TIME_MILLI_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 获取当前年份（字符串）
	 * @return
	 */
	public static String getYearString() {
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份（单数月份前填充0）
	 * @return
	 */
	public static String getMonthString() {
		int imonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (imonth < 10) {
			return "0" + imonth;
		} else {
			return String.valueOf(imonth);
		}
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 把当前年份和月份修改为字符串格式
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getString(int year, int month) {
		if(month < 10) {
			return year + "0" + month;
		} else {
			return String.valueOf(year) + String.valueOf(month);
		}
	}
	
	/**
	 * 获取当前日期（单数日期前填充0）
	 * @return
	 */
	public static String getDay() {
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		} else {
			return String.valueOf(day);
		}
	}

	/**
	 * 判断今天是否是周末
	 * 
	 * @return
	 */
	public static boolean isWeekend() {
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1 || dayOfWeek == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取系统当前时间HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat(TIME);
		return df.format(date);
	}

	/**
	 * 返回系统当前日期时间(精确到秒)
	 * 
	 * @return String 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getDateTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat(DATE_TIME);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期时间(精确到秒)
	 * 
	 * @return String 以yyyy-MM-dd HH:mm:ss为格式的当前系统时间
	 */
	public static String getDateTimeFormat() {
		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期年月日(精确到天)
	 * 
	 * @return String 以yyyyMMdd为格式的当前系统日期
	 */
	public static String getDate() {
		return new SimpleDateFormat(DATE).format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 获取UNIX时间戳格式的系统当前日期（精确到天）
	 * @return
	 */
	public static Long getDateUNIX() {
		try {
			return new SimpleDateFormat(DATE).parse(getDate()).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	/**
	 * 获取系统当前日期时间(精确到毫秒)
	 * 
	 * @return String 以yyyy-MM-dd HH:mm:ss.SSS为格式的当前系统时间
	 */
	public static String getDateTimeMilliFormat() {
		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat(DATE_TIME_MILLI_FORMAT);
		return df.format(date);
	}
	
	/**
	 * 把传入的日期改为unix时间戳格式（传入日期格式为yyyy-MM-dd HH:mm:ss）
	 * @param dateTime
	 * @return
	 */
	public static Long dateTimeFormatToUNIX(String dateTime) {
		try {
			return new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
	/**
	 * 把传入的日期改为unix时间戳格式（传入日期格式为yyyyMMddHHmmss）
	 * @param dateTime
	 * @return
	 */
	public static Long dateTimeToUNIX(String dateTime) {
		try {
			return new SimpleDateFormat(dateTime).parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	/**
	 * 获取系统当前日期（精确到天）
	 * 
	 * @return String 以yyyy-MM-dd为格式的当前系统日期
	 */
	public static String getDateFormat() {
		Date dt = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(dt);
	}

	/**
	 * 将yyyyMMddHHmmss格式的时间改为yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param String
	 *            dateCurrent yyyyMMddHHmmss格式的时间
	 * @return String yyyy-MM-dd HH:mm:ss格式的时间
	 */
	public static String dateCurrentToDateFormatter(String dateCurrent) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME);
		Date date = Calendar.getInstance().getTime();
		try {
			date = dateFormatter.parse(dateCurrent);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return dateFormatter.format(date);
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的时间改为yyyyMMddHHmmss格式
	 * 
	 * @param String
	 *            dateFormatter yyyy-MM-dd HH:mm:ss格式的时间
	 * @return String yyyyMMddHHmmss格式的时间
	 */
	public static String dateFormatterToDateCurrent(String dateFormatter) {
		SimpleDateFormat dateCurrent = new SimpleDateFormat(DATE_TIME_FORMAT);
		Date date = Calendar.getInstance().getTime();
		try {
			date = dateCurrent.parse(dateFormatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateCurrent = new SimpleDateFormat(DATE_TIME);
		return dateCurrent.format(date);
	}

}
