import com.mingrn.common.global.annotation.ParamsCheck;
import com.mingrn.common.global.annotation.ParamsIsNotNull;
import com.mingrn.common.global.exception.NotLoginException;
import com.mingrn.common.global.result.ResponseMsgUtil;
import com.mingrn.common.global.result.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SpringBootApplication
public class GlobalHandleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GlobalHandleApplication.class).web(WebApplicationType.SERVLET).run(args);
	}


	@ParamsCheck
	@GetMapping("/notLoginException")
	public Result notLoginException(@ParamsIsNotNull @RequestParam String name,
									@RequestParam(required = false) String value) {
		return ResponseMsgUtil.success("这是成功请求示例");
	}

	@GetMapping("/success")
	public Result success() {
		return ResponseMsgUtil.success("这是成功请求示例");
	}


	private void notLoginExceptionTest() {
		throw new NotLoginException();
	}

}