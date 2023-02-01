package com.connect.brick.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class StaticResourceConfig extends WebMvcConfigurationSupport {

	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";

	@Value("${resource.upload.image.locations}")
	private String resourceUploadImagePath;
	
	@Value("${spring.resources.static-locations}")
	private String resourcesStaticPath;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//이미지 저장소
		registry.addResourceHandler("/image_storage/**").addResourceLocations(resourceUploadImagePath);

		//수정용 외부 리소스 (디자인 파일)
		registry.addResourceHandler("/resources/**").addResourceLocations(resourcesStaticPath);
		
		//내부 리소스
		registry.addResourceHandler("/assets/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "assets/");
		
		//내부 리소스
		registry.addResourceHandler("/lib/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "lib/");
				
		//개발용 JS 파일
		//registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "js/");
		//=> 리소스 패쓰 등록이 서로 다르면 JS 파일을 서로 부를때 오류가 생김. => 크로스 컴파일 문제인듯
	}
	
	
	//Pageable 사용하기 위해 
	@Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
