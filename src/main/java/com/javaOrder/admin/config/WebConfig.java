package com.javaOrder.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    // 로컬 파일 시스템의 C:/uploads/images/ 디렉토리를 /images/ 경로로 서빙
	    registry.addResourceHandler("/images/**")
	            .addResourceLocations("file:///C:/uploads/images/", "classpath:/static/images/")
	            .setCachePeriod(0);
	    
	}

}
