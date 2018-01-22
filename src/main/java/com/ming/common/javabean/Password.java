package com.ming.common.javabean;

import java.io.Serializable;

import com.ming.common.util.bussiness.SaltUtil;
import com.ming.common.util.common.StringUtil;

/**
 * 类名：PassWord<br>
 * 功能：密码对象<br>
 * 版本：1.0<br>
 * 日期：2017-01-11<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */
public class Password implements Serializable {

	private static final long serialVersionUID = -4906963138661339856L;

	/** 密码 */
	private String password;
	/** 密码哈希盐 */
	private String salt;
	
	/**
	 * 创建此类时自动获取默认盐
	 */
	public Password() {	
		this.salt = SaltUtil.getRandom();
	}

	
	/** 密码 */
	public String getPassword() {
		return password;
	}

	/** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 加密密码(传入密码哈希盐，将此类中密码加密）*/
	public void hashPassword(String salt) {
		this.password = SaltUtil.shaEncrypt(this.password + salt);
	}

	/** 加密密码（使用默认盐为密码加密） */
	public void hashPassword() {
		this.password = SaltUtil.shaEncrypt(this.password + this.salt);
	}

	/** 密码哈希盐 */
	public String getSalt() {
		return salt;
	}

	/** 密码哈希盐 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Password) {
			Password pass = (Password) obj;
			if (this.password.equals(pass.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean empty() {
		return StringUtil.isNullOrEmpty(this.password);
	}
}
