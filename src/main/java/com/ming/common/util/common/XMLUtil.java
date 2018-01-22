package com.ming.common.util.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 类名：XMLUtil<br>
 * 功能：XML解析<br>
 * 版本：1.0<br>
 * 日期：2017-01-20<br>
 * 说明：暂无
 * 
 * @author Ming
 */
public class XMLUtil {
	
	/**
	 * 将Map数组转换为xml格式的字符串
	 * 
	 * @param Map<String,String>
	 *            xmlmap 待转换的Map数组
	 * @return String 转换后的xml
	 * @throws UnsupportedEncodingException
	 */
	public static String setXML(SortedMap<String, String> xmlmap) {
		StringBuffer xml = new StringBuffer("<xml>");
		Set<Entry<String, String>> es = xmlmap.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if (v != null && !v.equals("") && !k.equals("key")) {
				xml.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		xml.append("</xml>");
		// ---测试用：将打包好的xml写入到日志中---
		// -------------------------------------
		return xml.toString();
	}

	public static String setXML(Map<String, String> xmlmap) {
		StringBuffer xml = new StringBuffer("<xml>");
		Set<Entry<String, String>> es = xmlmap.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (v != null && !v.equals("") && !k.equals("key")) {
				xml.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		xml.append("</xml>");
		// ---测试用：将打包好的xml写入到日志中---
		// -------------------------------------
		return xml.toString();
	}


	/**
	 * 解析xml，返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据
	 * 
	 * @param String
	 *            xml 传递回来的xml格式的字符串
	 * @return String prepay_id预支付交易会话标识
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getXML(String strxml) {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map<String, String> m = new HashMap<String, String>();
		InputStream in = null ;
		SAXBuilder builder = new SAXBuilder();
		try {
			in = new ByteArrayInputStream(strxml.getBytes("UTF-8")); 
			Document doc = builder.build(in);
			Element root = doc.getRootElement();
			List list = root.getChildren();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String k = e.getName();
				String v = "";
				List children = e.getChildren();
				if (children.isEmpty()) {
					v = e.getTextNormalize();
				} else {
					v = getChildrenText(children);
				}
				m.put(k, v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	private static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
}
