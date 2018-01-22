package com.ming.common.javabean;

/**
 * 类名：ResponseBean<br>
 * 功能：响应实体<br>
 * 版本：1.0<br>
 * 日期：2017-01-10<br>
 * 说明：暂无
 * @author Ming
 *
 * @param <V> 返回内容
 */
public class Result<V> {
	
	/** 成功返回值 */
	public static final int SUCCESS = 1;
	/** 失败返回值 */
	public static final int FAIL = 0;
	/** 错误码0：其它错误 */
	public static final int ERRORCODE = 0;
	/** 错误码1：用户未登录 */
	public static final int UNLOGIN = 1;
	/** 错误码2：用户未设置支付密码 */
	public static final int NOPAYPASS = 2;
	/** 错误码3：无数据 */
	public static final int NO_DATA = 3;

	/** 返回结果代码：1为成功/0为失败 */
	private int code;
	/** 错误代码，当code为0时返回 */
	private Integer errorCode;
	/** 返回消息，当code为0时返回 */
	private String msg;
	/** 返回值内容 */
	private V body;
	/** 页码对象（当需要分页时返回） */
	private Page page;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public V getBody() {
		return body;
	}

	public void setBody(V body) {
		this.body = body;
	}
	
	public void removeBody() {
		this.body = null;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
