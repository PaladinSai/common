package com.ming.common.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * 类名：ClassUtil<br>
 * 功能：类工具包<br>
 * 版本：1.0<br>
 * 日期：2016-09-19<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */

public class ClassUtil {
	/**
	 * 获取某个接口的所有实现类<br>
	 * 条件：该接口实现类与接口在同一包或其子包中
	 * 
	 * @param clazz接口的Calss对象
	 * @return
	 */
	public static ArrayList<Class<?>> getAllClassByInterface(Class<?> clazz) throws Exception {
		ArrayList<Class<?>> list = new ArrayList<>();
		// 判断是否是一个接口
		if (clazz.isInterface()) {
			ArrayList<Class<?>> allClass = getAllClass(clazz.getPackage().getName());
			// 循环判断路径下的所有类是否实现了指定的接口 并且排除接口类自己
			for (int i = 0; i < allClass.size(); i++) {
				// 判断是不是同一个接口
				if (clazz.isAssignableFrom(allClass.get(i))) {
					if (!clazz.equals(allClass.get(i))) {// 自身并不加进去
						list.add(allClass.get(i));
					}
				}
			}
		}
		return list;
	}

	/**
	 * 从一个指定路径下查找所有的类
	 * 
	 * @param name
	 * @throws Exception
	 */
	private static ArrayList<Class<?>> getAllClass(String packagename) throws Exception {
		ArrayList<Class<?>> list = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packagename.replace('.', '/');
		ArrayList<File> fileList = new ArrayList<>();
		Enumeration<URL> enumeration = classLoader.getResources(path);
		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();
			fileList.add(new File(url.getFile()));
		}
		for (int i = 0; i < fileList.size(); i++) {
			list.addAll(findClass(fileList.get(i), packagename));
		}
		return list;
	}

	/**
	 * 如果file是文件夹，则递归调用findClass方法，或者文件夹下的类<br>
	 * 如果file本身是类文件，则加入list中进行保存，并返回
	 * 
	 * @param file
	 * @param packagename
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static ArrayList<Class<?>> findClass(File file, String packagename) throws ClassNotFoundException {
		ArrayList<Class<?>> list = new ArrayList<>();
		if (!file.exists()) {
			return list;
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				// 添加断言用于判断
				assert !file2.getName().contains(".");
				ArrayList<Class<?>> arrayList = findClass(file2, packagename + "." + file2.getName());
				list.addAll(arrayList);
			} else if (file2.getName().endsWith(".class")) {
				// 保存的类文件不需要后缀.class
				list.add(Class.forName(packagename + '.' + file2.getName().substring(0, file2.getName().length() - 6)));
			}
		}
		return list;
	}
}
