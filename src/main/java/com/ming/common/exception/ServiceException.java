package com.ming.common.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 2300042389200757799L;
	private Integer code;
	private String msg;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ServiceException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}

}
