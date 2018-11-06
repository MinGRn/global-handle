package com.mingrn.common.global.constants;

/**
 * 全局异常状态码常量
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 */
public final class ResponseStatusConstant {

	/**
	 * 失败状态码
	 */
	public static final int REQUEST_FAIL = -1;

	/**
	 * 请求成功状态码
	 */
	public static final int REQUEST_SUCCESS = 0;

	/**
	 * 非法请求状态码
	 */
	public static final int REQUEST_ILLEGAL = 10000;

	/**
	 * 未登录状态码
	 */
	public static final int REQUEST_NOT_LOGIN = 10001;

	/**
	 * 用户名或密码错误状态码
	 */
	public static final int REQUEST_USER_OR_PASSWORD_ERR = 10002;

	/**
	 * 密码错误次数已达上限
	 */
	public static final int REQUEST_PASSWORD_ERROR_LIMIT = 10003;

	/**
	 * 请求参数不能为空状态码
	 */
	public static final int REQUEST_PARAMS_CANT_NOT_EMPTY = 10004;

	/**
	 * 无操作权限状态吗
	 */
	public static final int REQUEST_NO_AUTHORIZED = 10005;

	/**
	 * 查询结果为空状态码
	 */
	public static final int REQUEST_RESULT_IS_EMPTY = 10006;


}
