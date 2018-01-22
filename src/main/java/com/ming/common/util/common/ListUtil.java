package com.ming.common.util.common;

import java.util.List;

import com.ming.common.javabean.Page;

/**
 * 类名：ListUtil<br>
 * 功能：将list分页<br>
 * 版本：1.0<br>
 * 日期：2017-03-28<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class ListUtil {

	/**
	 * list分页
	 * @param list
	 * @param page
	 * @return
	 */
	public static <T> List<T> listFlip(List<T> list, Page page) {
		return page.getPageSize()* page.getPageNum() <= list.size() ? list.subList(page.getPageSize() * (page.getPageNum() - 1),
				(page.getPageSize() * page.getPageNum()) > list.size() ? list.size()
						: page.getPageSize() * page.getPageNum()) : list;
	}

}
