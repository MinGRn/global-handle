# global-handle
Version：`0.0.1-SNAPSHOT`

global-handle 是一个统一全局处理工程，包含：

* 全局异常处理
* 消息统一响应

# 包结构

```bash
src
├── main
│   ├── java
│   │   └── com.mingrn.common.global
│   │       ├── constants                   # 常量包
│   │       ├── enums                       # 枚举包
│   │       ├── exception                   # 自定义异常包
│   │       ├── handle                      # 异常处理包
│   │       ├── result                      # 统一响应包
│   │       └── util                        # 工具类
│   └── resources
└── test
    └── java
```

# pom 依赖引用

```xml
<dependency>
	<groupId>com.mingrn.common.global</groupId>
	<artifactId>global-handle</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

该包已经包含如下 `springBoot` 依赖:

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>

<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>fastjson</artifactId>
	<version>${fastjson.version}</version>
</dependency>
```

SpringBoot 版本是 `2.0.3.RELEASE`在构建 SpringBoot 时不需要重复引入。新工程启动类如下所示：

```java
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.mingrn.common.global"})
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}
}
```

**注意：** 这里一定要使用 `@ComponentScan` 并扫描 `com.mingrn.common.global` 包下所有组件。否则该包下全局异常处理与统一响应功能
无法正常使用。另外，在启动类中建议不要使用如下代码作为启动方式！

```java
public static void main(String[] args) {
	SpringApplication.run(SpringcloudEurekaApplication.class, args);
}
```

具体使用示例见 [GlobalHandleApplication](src/test/java/GlobalHandleApplication.java) 

----

# 全局异常处理说明

全局异常处理类继承至 `org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController`，实例：

```java
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;

@RestController
@RequestMapping
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractErrorController {

	public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}


	@Override
	public String getErrorPath() {
		return null;
	}
}
```

当前拦截异常范围如下所示：

`404 错误`、`500 错误`、`未登录异常`、`参数不能为空异常` ...