package com.mingrn.common.global.handle;

import com.mingrn.common.global.exception.NoOperateAuthorityException;
import com.mingrn.common.global.exception.NotLoginException;
import com.mingrn.common.global.exception.ParamIsNotNullException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import com.mingrn.common.global.util.RequestUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 匹配中括号字符正则表达式,遇空格返回
	 * e.g:
	 * String str = "request [class java.lang.String] parameter [name] must not be empty or null";
	 * result:
	 * matcher1: java.lang.String
	 * matcher2: name
	 */
	private static final String REGEX_BRACKET_CHARACTER = "[^\\[\\s]+(?=])";


	/**
	 * 匹配中括号全部字符正则表达式
	 * e.g:
	 * String str = "request [class java.lang.String] parameter [name] must not be empty or null";
	 * result:
	 * matcher1: class java.lang.String
	 * matcher2: name
	 */
	private static final String REGEX_BRACKET_ALL_CHARACTER = "[^\\[]+(?=])";


	/**
	 * 正则匹配
	 */
	private static final Pattern PATTERN_BRACKET_CHARACTER_EXTRACTIONS = Pattern.compile(REGEX_BRACKET_CHARACTER, Pattern.MULTILINE);


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
	public Result noHandlerFoundException(HttpServletRequest request, Exception e) {
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
	public Result serverError(HttpServletRequest request, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception:{}", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.internalServerErr();
	}


	/**
	 * 未登录异常
	 *
	 * @see ResponseMsgUtil#notLogin(String)
	 */
	@ExceptionHandler(NotLoginException.class)
	public Result notLoginException(HttpServletRequest request, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.notLogin(e.getMessage());
	}


	/**
	 * 参数不能为空异常
	 * 使用正则表达式匹配中括号字符
	 * 规则表达式{@link GlobalExceptionHandler#REGEX_BRACKET_CHARACTER}
	 *
	 * @see ResponseMsgUtil#paramsCanNotEmpty(String, String)
	 */
	@ExceptionHandler(ParamIsNotNullException.class)
	public Result paramIsNotNullException(HttpServletRequest request, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		// 匹配中括号字符,作为参数类型与参数名
		String parameterType = null, parameterName = null;
		final Matcher matcher = PATTERN_BRACKET_CHARACTER_EXTRACTIONS.matcher(e.getMessage());
		while (matcher.find()) {
			if (StringUtils.isBlank(parameterType)) {
				parameterType = matcher.group(0);
			} else {
				parameterName = matcher.group(0);
			}
		}
		return ResponseMsgUtil.paramsCanNotEmpty(parameterType, parameterName);
	}


	/**
	 * 无操作权限异常
	 *
	 * @see ResponseMsgUtil#noAuthorized(String)
	 */
	@ExceptionHandler(NoOperateAuthorityException.class)
	public Result noOperateAuthorityException(HttpServletRequest request, Exception e) {
		LOGGER.error("!!! request uri:{} from {} server exception", request.getRequestURI(), RequestUtils.getIpAddress(request), e);
		return ResponseMsgUtil.noAuthorized(e.getMessage());
	}


	/**
	 * 重写/error请求
	 * {@link GlobalExceptionHandler#errorPath}
	 *
	 * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "${server.error.path:${error.path:/error}}")
	public Result<String> handleErrors(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		if (status == HttpStatus.NOT_FOUND) {
			return noHandlerFoundException(request, null);
		}
		return ResponseMsgUtil.internalServerErr();
	}


	@Override
	public String getErrorPath() {
		return errorPath;
	}
}
