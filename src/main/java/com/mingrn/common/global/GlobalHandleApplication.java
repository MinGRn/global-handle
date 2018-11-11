package com.mingrn.common.global;

import com.mingrn.common.global.annotation.Checked;
import com.mingrn.common.global.annotation.ParamsIsNotNull;
import com.mingrn.common.global.exception.NoOperateAuthorityException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
@Api(description = "Swagger API")
@RestController
@RequestMapping
@SpringBootApplication
public class GlobalHandleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GlobalHandleApplication.class).web(WebApplicationType.SERVLET).run(args);
	}


	@Checked
	@GetMapping("/notLoginException1")
	@ApiOperation(value = "未登录异常",notes = "未登录异常测试")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name",value = "名称",paramType = "query",dataType = "String",required = true),
			@ApiImplicitParam(name = "value",value = "值",paramType = "query",dataType = "String",required = true)
	})
	public Result notLoginException1(@ParamsIsNotNull @RequestParam String name,
									 @RequestParam(required = false) String value) {
		HashMap<String, Object> map = null;
		return ResponseMsgUtil.success(map);
	}

	@Checked
	@GetMapping("/notLoginException2")
	public Result notLoginException2(@RequestParam String name,
									 @RequestParam(required = false) String value) {
		notLoginExceptionTest();
		return ResponseMsgUtil.success(null);
	}

	@Checked
	@GetMapping("/notLoginException3")
	public Result notLoginException3(@ParamsIsNotNull @RequestParam String name,
									 @RequestParam(required = false) String value) {
		ArrayList<String> list = null;
		return ResponseMsgUtil.success(list);
	}

	@Checked
	@GetMapping("/notLoginException4")
	public Result notLoginException4(@ParamsIsNotNull @RequestParam String name,
									 @RequestParam(required = false) String value) {
		return ResponseMsgUtil.success("");
	}

	@GetMapping("/success")
	public Result success() {
		return ResponseMsgUtil.success("这是成功请求示例");
	}


	private void notLoginExceptionTest() {
		throw new NoOperateAuthorityException();
	}

}