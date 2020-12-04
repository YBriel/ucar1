package com.yzcx.ucar.common.utils.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Spring Boot<=1.3 无需定义，Spring Boot自动为您定义了一个。
   Spring Boot >= 1.4 Spring Boot不再自动定义一个RestTemplate，
             而是定义了一个RestTemplateBuilder允许您更好地控制所RestTemplate创建的对象
 * @author maozh
 *
 */
@Configuration
public class RestTemplateConfigure {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
