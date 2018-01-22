package com.ming.common.constant.config;

import org.dozer.DozerBeanMapper;

import com.ming.common.util.IdWorker;

public class Config {
	
	/** 编码方式 */
	public static String CHAR_SET = "utf-8";
	/** id生成器 */
	public static final IdWorker ID_WORKER = new IdWorker(0);
	/** dozer，javabean工具 */
	public static final DozerBeanMapper dozer = new DozerBeanMapper();
	/** netty监听端口 */
	public static final int PORT = 26000;

}
