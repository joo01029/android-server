package com.out.android.config;

import com.out.android.config.intercepter.RequestLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new RequestLogInterceptor())
				.addPathPatterns("/**");

	}
}
