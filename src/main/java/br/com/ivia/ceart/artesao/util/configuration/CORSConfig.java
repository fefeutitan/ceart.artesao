package br.com.ivia.ceart.artesao.util.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig {
	
	@Bean
	public FilterRegistrationBean<?> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200/");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize("20MB");
	    factory.setMaxRequestSize("500MB");
	    return factory.createMultipartConfig();
	}
}
