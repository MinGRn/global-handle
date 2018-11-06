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
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_FAILURE.getCode(), ResponseStatusCodeEnum.RESULT_CODE_FAILURE.getMsg(), null);
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
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_SUCCESS.getCode(), ResponseStatusCodeEnum.RESULT_CODE_SUCCESS.getMsg(), data);
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
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_ILLEGAL_REQUEST.getCode(), ResponseStatusCodeEnum.RESULT_CODE_ILLEGAL_REQUEST.getMsg(), null);
	}

	/**
	 * 未登录
	 */
	public static Result notLogin() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_NOT_LOGIN.getCode(), ResponseStatusCodeEnum.RESULT_CODE_NOT_LOGIN.getMsg(), null);
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
	public static Result maxPasswordErrorLimit() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_PASSWORD_ERROR_LIMIT.getCode(), ResponseStatusCodeEnum.RESULT_CODE_PASSWORD_ERROR_LIMIT.getMsg(), null);
	}


	/**
	 * 参数不能为空
	 */
	public static Result paramsCanNotEmpty() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_PARAMS_CANT_NOT_EMPTY.getCode(), ResponseStatusCodeEnum.RESULT_CODE_PARAMS_CANT_NOT_EMPTY.getMsg(), null);
	}

	/**
	 * 无操作权限
	 */
	public static Result noauthorized() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_NO_AUTHORIZED.getCode(), ResponseStatusCodeEnum.RESULT_CODE_NO_AUTHORIZED.getMsg(), null);
	}

	/**
	 * 结果不存在或空集合
	 */
	public static Result resultIsEmptyOrNotExist() {
		return builderResponse(ResponseStatusCodeEnum.RESULT_CODE_RESULT_IS_EMPTY.getCode(), ResponseStatusCodeEnum.RESULT_CODE_RESULT_IS_EMPTY.getMsg(), null);
	}
}