package com.mingrn.common.global.handle;

import com.mingrn.common.global.exception.NotLoginException;
import com.mingrn.common.global.exception.ParamIsNotNullException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import com.mingrn.common.global.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 */
@RestController
@RequestMapping
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractErrorController {

	public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	/**
	 * 404 错误
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public void noHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception e) {

	}

	/**
	 * 500 错误
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void serverError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception:{}", request.getRequestURI(), RequestUtil.getIpAddress(request), e);
	}


	/**
	 * 未登录异常
	 */
	@ExceptionHandler(NotLoginException.class)
	public Result notLoginException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtil.getIpAddress(request), e);
		return ResponseMsgUtil.notLogin();
	}

	/**
	 * 参数不能为空异常
	 */
	@ExceptionHandler(ParamIsNotNullException.class)
	public Result paramIsNotNullException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtil.getIpAddress(request), e);
		return ResponseMsgUtil.paramsCanNotEmpty();
	}


	@Override
	public String getErrorPath() {
		return null;
	}
}
