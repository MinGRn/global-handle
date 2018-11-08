package com.mingrn.common.global.handle;

import com.mingrn.common.global.exception.NotLoginException;
import com.mingrn.common.global.exception.ParamIsNotNullException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import com.mingrn.common.global.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
	 * ${server.error.path:${error.path:/error}}释义:
	 * 查看在配置文件中是否配置了server.error.path值,若配置则使用指定的值作为错误请求;
	 * 如果未配置,则查看是否配置了error.path;如果还是没有，则该控制器默认处理/error请求
	 *
	 * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
	 */
	@Value("${server.error.path:${error.path:/error}}")
	private static String errorPath = "/error";

	/**
	 * 404 错误
	 *
	 * @see ResponseMsgUtil#requestNotFound()
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public Result noHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception:{}", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.requestNotFound();
	}

	/**
	 * 500 错误,内部服务器异常
	 *
	 * @see ResponseMsgUtil#internalServerErr()
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Result serverError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception:{}", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.internalServerErr();
	}


	/**
	 * 未登录异常
	 *
	 * @see ResponseMsgUtil#notLogin()
	 */
	@ExceptionHandler(NotLoginException.class)
	public Result notLoginException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.notLogin();
	}

	/**
	 * 参数不能为空异常
	 *
	 * @see ResponseMsgUtil#paramsCanNotEmpty()
	 */
	@ExceptionHandler(ParamIsNotNullException.class)
	public Result paramIsNotNullException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.paramsCanNotEmpty();
	}

	/**
	 * 非法参数、访问请求
	 * @see ResponseMsgUtil#illegalRequest(String)
	 */
	@ExceptionHandler({IllegalArgumentException.class,IllegalAccessException.class})
	public Result illegalRequest(HttpServletRequest request, HttpServletResponse response, Exception e){
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.illegalRequest();
	}

	/**
	 * 重写/error请求
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "${server.error.path:${error.path:/error}}")
	public Result<String> handleErrors(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		if (status == HttpStatus.NOT_FOUND) {
			return noHandlerFoundException(request, response, null);
		}
		return ResponseMsgUtil.internalServerErr();
	}


	@Override
	public String getErrorPath() {
		return errorPath;
	}
}
