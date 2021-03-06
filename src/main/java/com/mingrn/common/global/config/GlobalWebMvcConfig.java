package com.mingrn.common.global.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mingrn.common.global.filter.CrossFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;

/**
 * 全局 WebMvc 配置类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 11/11/2018 12:24
 */
@Configuration
public class GlobalWebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Swagger-ui 资源映射
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	/**
	 * 使用 FastJson 替换 JackJson 解析 JSON 数据
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		/*
		 * 转换为JSON字符串,默认:
		 * WriteMapNullValue       是否输出值为null的字段,默认为false
		 * WriteNullListAsEmpty    List字段如果为null,输出为[],而非null
		 * WriteNullStringAsEmpty  字符类型字段如果为null,输出为"",而非null
		 * WriteDateUseDateFormat  日期输出使用日期格式化
		 */
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return new HttpMessageConverters(fastJsonHttpMessageConverter);
	}


	/**
	 * 跨域
	 */
	@Bean
	@SuppressWarnings("unchecked")
	public FilterRegistrationBean crossFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CrossFilter());
		registrationBean.addUrlPatterns("/**");
		registrationBean.setAsyncSupported(true);
		registrationBean.setName("crossFilter");
		registrationBean.setOrder(1);
		return registrationBean;
	}


	/**
	 * 编码
	 */
	@Bean
	@SuppressWarnings("unchecked")
	public FilterRegistrationBean encodingFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncoding = new CharacterEncodingFilter();
		characterEncoding.setForceEncoding(true);
		characterEncoding.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncoding);
		registrationBean.setName("encodingFilter");
		registrationBean.setOrder(2);
		return registrationBean;
	}
}