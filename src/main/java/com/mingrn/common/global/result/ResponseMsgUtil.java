package com.mingrn.common.global.result;

import com.mingrn.common.global.enums.ResponseStatusCodeEnum;

/**
 * 消息统一相应工具类
 *
 * @author MinGRn
 */
public class ResponseMsgUtil {

	private ResponseMsgUtil() {
	}


	/**
	 * 统一返回结果
	 */
	public static <T> Result<T> builderResponse(int code, String msg, T data) {
		Result<T> res = new Result<>();
		res.setResCode(code);
		res.setResMsg(msg);
		res.setData(data);
		return res;
	}


	/**
	 * 请求失败
	 */
	public static <T> Result<T> failure() {
		return failure(ResponseStatusCodeEnum.RESULT_CODE_FAILURE.getMsg());
	}


	/**
	 * 请求失败
	 */
	public static <T> Result<T> failure(String msg) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_FAILURE.getCode(), msg, null);
	}


	/**
	 * 请求成功
	 */
	public static <T> Result<T> success(T data) {
		return success(ResponseStatusCodeEnum.RESULT_CODE_SUCCESS.getMsg(), data);
	}


	/**
	 * 请求成功
	 */
	public static <T> Result<T> success(String msg, T data) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_SUCCESS.getCode(), msg, data);
	}


	/**
	 * 非法请求
	 */
	public static <T> Result<T> illegalRequest() {
		return illegalRequest(ResponseStatusCodeEnum.RESULT_CODE_ILLEGAL_REQUEST.getMsg());
	}


	/**
	 * 非法请求
	 */
	public static <T> Result<T> illegalRequest(String msg) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_ILLEGAL_REQUEST.getCode(), msg, null);
	}


	/**
	 * 未登录
	 */
	public static Result notLogin() {
		return notLogin(ResponseStatusCodeEnum.RESULT_CODE_NOT_LOGIN.getMsg());
	}


	/**
	 * 未登录
	 */
	public static Result notLogin(String meg) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_NOT_LOGIN.getCode(), meg, null);
	}


	/**
	 * 登录用户名或密码错误
	 */
	public static Result loginUserOrPasswordError() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_USER_OR_PASSWORD_ERROR.getCode(), ResponseStatusCodeEnum.RESULT_CODE_USER_OR_PASSWORD_ERROR.getMsg(), null);
	}


	/**
	 * 密码错误次数已达上限
	 */
	public static Result passwordErrorLimit() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_PASSWORD_ERROR_LIMIT.getCode(), ResponseStatusCodeEnum.RESULT_CODE_PASSWORD_ERROR_LIMIT.getMsg(), null);
	}


	/**
	 * 参数不能为空
	 */
	public static Result paramsCanNotEmpty(String parameterType, String parameterName) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_PARAMS_CANT_NOT_EMPTY.getCode(), String.format("[%s] parameter [%s] can not be null or empty", parameterType, parameterName), null);
	}


	/**
	 * 无操作权限
	 */
	public static Result noAuthorized() {
		return noAuthorized(ResponseStatusCodeEnum.RESULT_CODE_NO_AUTHORIZED.getMsg());
	}


	/**
	 * 无操作权限
	 */
	public static Result noAuthorized(String msg) {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_NO_AUTHORIZED.getCode(), msg, null);
	}


	/**
	 * 结果不存在或空集合
	 */
	public static Result resultIsEmptyOrNotExist() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_RESULT_IS_EMPTY.getCode(), ResponseStatusCodeEnum.RESULT_CODE_RESULT_IS_EMPTY.getMsg(), null);
	}


	/**
	 * 404
	 */
	public static Result requestNotFound() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_NOT_FOUND.getCode(), ResponseStatusCodeEnum.RESULT_CODE_NOT_FOUND.getMsg(), null);
	}


	/**
	 * 500 错误
	 */
	public static Result internalServerErr() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_INTERNAL_SERVER_ERR.getCode(), ResponseStatusCodeEnum.RESULT_CODE_INTERNAL_SERVER_ERR.getMsg(), null);
	}


	/**
	 * 业务异常
	 */
	public static Result serviceException() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_SERVICE_EXCEPTION.getCode(), ResponseStatusCodeEnum.RESULT_CODE_SERVICE_EXCEPTION.getMsg(), null);
	}
}