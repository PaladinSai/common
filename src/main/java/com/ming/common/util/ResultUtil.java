package com.ming.common.util;

import com.ming.common.javabean.Page;
import com.ming.common.javabean.Result;

/**
 * 类名：ResultUtil<br>
 * 功能：返回数据打包<br>
 * 版本：1.0<br>
 * 日期：2017-01-13<br>
 * 说明：暂无
 * 
 * @author Ming
 *
 */

public class ResultUtil {

	/**
	 * 成功，无返回数据
	 * 
	 * @return
	 */
	public static <T> Result<T> success() {
		Result<T> result = new Result<>();
		result.setCode(Result.SUCCESS);
		return result;
	}

	/**
	 * 成功，返回body
	 * 
	 * @param body
	 * @return
	 */
	public static <T> Result<T> success(T body) {
		Result<T> result = new Result<>();
		result.setCode(Result.SUCCESS);
		result.setBody(body);
		return result;
	}

	/**
	 * 成功，返回body和page
	 * 
	 * @param body
	 * @param page
	 * @return
	 */
	public static <T> Result<T> success(T body, Page page) {
		Result<T> result = new Result<>();
		result.setCode(Result.SUCCESS);
		result.setBody(body);
		result.setPage(page);
		return result;
	}

	/**
	 * 失败，其它错误。错误码0
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> fail(String msg) {
		Result<T> result = new Result<>();
		result.setCode(Result.FAIL);
		result.setErrorCode(Result.ERRORCODE);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> fail(int errorCode, String msg) {
		Result<T> result = new Result<>();
		result.setCode(Result.FAIL);
		result.setErrorCode(errorCode);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 失败，未登录。错误码1
	 * 
	 * @return
	 */
	public static <T> Result<T> failUnLogin() {
		Result<T> result = new Result<>();
		result.setCode(Result.FAIL);
		result.setErrorCode(Result.UNLOGIN);
		return result;
	}

	/**
	 * 失败，用户未设置支付密码，错误码2
	 * 
	 * @return
	 */
	public static <T> Result<T> failNoPayPass() {
		Result<T> result = new Result<>();
		result.setCode(Result.FAIL);
		result.setErrorCode(Result.NOPAYPASS);
		return result;
	}

	/**
	 * 失败，无数据，错误码3
	 * 
	 * @return
	 */
	public static <T> Result<T> failNoData() {
		Result<T> result = new Result<>();
		result.setCode(Result.FAIL);
		result.setErrorCode(Result.NO_DATA);
		return result;
	}

}
