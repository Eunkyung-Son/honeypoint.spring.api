package com.ek.honeypoint.config;

import com.ek.honeypoint.common.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.allowedHeaders("*")
            .exposedHeaders("Access-Control-Allow-Origin",
                    "Access-Control-Allow-Methods",
                    "Access-Control-Allow-Headers",
                    "Access-Control-Max-Age",
                    "Access-Control-Request-Headers",
                    "Access-Control-Request-Method")
            .maxAge(3600);
	}

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                LoginInterceptor loginInterceptor = new LoginInterceptor();
                registry.addInterceptor(loginInterceptor)
                        .addPathPatterns()
                        .excludePathPatterns();
        }
}