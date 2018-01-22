package com.ming.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 类名：PropUtils
 * 功能：存取properties（java配置文件）
 * 版本：1.0
 * 日期：2015-08-21
 * 说明：暂无
 * @author Ming
 *
 */
public class PropUtil {
	
	public static String config_path = "\\config.properties";

	/**
	 * 从properties文件中读取键的值
	 * 文件路径存储在Config中
	 * @param key 键名
	 * @return values 键值
	 */
	public static String propRead(String key) {
		String values = null;
		Properties prop = new Properties();
		File file = new File(config_path);
		try {
			if (file.exists()) {
				prop.load(new FileInputStream(config_path));
				values = prop.getProperty(key);
			} else {
				// 文件不存在，返回错误信息
				values = "配置文件不存在！";
			}
			return values;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return values;
		} catch (IOException e) {
			e.printStackTrace();
			return values;
		}
	}

	/**
	 * 向properties文件中存储键值
	 * 文件路径存储字Config类中
	 * @param key 键名
	 * @param value 键值
	 * @return boolean 是否成功
	 */
	public static boolean propWrite(String key, String value) {
		Properties prop = new Properties();
		File file = new File(config_path);

		try {
			if (file.exists()) {
				prop.load(new FileInputStream(config_path));
				// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
				// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
				OutputStream fos = new FileOutputStream(config_path);
				prop.setProperty(key, value);
				// 以适合使用 load 方法加载到 Properties 表中的格式，
				// 将此 Properties 表中的属性列表（键和元素对）写入输出流
				prop.store(fos, "Update '" + key + "' value");
			}else{
				//文件不存在，创建文件
				//file.mkdirs();
				file.createNewFile();
				prop.load(new FileInputStream(config_path));
				// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
				// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
				OutputStream fos = new FileOutputStream(config_path);
				prop.setProperty(key, value);
				// 以适合使用 load 方法加载到 Properties 表中的格式，
				// 将此 Properties 表中的属性列表（键和元素对）写入输出流
				prop.store(fos, "Update '" + key + "' value");
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
