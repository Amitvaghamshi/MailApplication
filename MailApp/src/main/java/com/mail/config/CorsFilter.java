package com.mail.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@Component
public class CorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletResponse response = (HttpServletResponse) res;
	       HttpServletRequest request = (HttpServletRequest) req;

	       response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	       response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	       response.setHeader("Access-Control-Allow-Headers", "*");
	       response.setHeader("Access-Control-Allow-Credentials", "true");
	       response.setHeader("Access-Control-Max-Age", "3600");

	       if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	           response.setStatus(HttpServletResponse.SC_OK);
	       } else {
	           chain.doFilter(req, res);
	       }
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", 
	    "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", 
	    "x-auth-token"));
	    configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
	    UrlBasedCorsConfigurationSource source = new 
	    UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}

}
