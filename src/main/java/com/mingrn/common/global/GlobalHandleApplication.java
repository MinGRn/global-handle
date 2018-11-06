package com.mingrn.common.global;

import com.mingrn.common.global.exception.NotLoginException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping
//@SpringBootApplication
public class GlobalHandleApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(GlobalHandleApplication.class, args);
	}


	@GetMapping("/notLoginException")
	public Result notLoginException() {
		notLoginExceptionTest();
		return ResponseMsgUtil.success("这是成功请求示例");
	}

	@GetMapping("/success")
	public Result success() {
		return ResponseMsgUtil.success("这是成功请求示例");
	}


	private void notLoginExceptionTest() {
		throw new NotLoginException();
	}*/

}
