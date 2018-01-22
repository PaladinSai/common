package com.ming.common.exception;

public class DaoException extends Exception {
	
	private static final long serialVersionUID = -9154734502559545785L;
	private Integer code;
	private String msg;
	
	public DaoException() {
		super();
	}
	
	public DaoException(String msg) {
		super(msg);
		this.msg = msg;
	}
		
	public DaoException(Integer code, String msg) {
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
