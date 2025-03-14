package com.alpha.www.Spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.alpha.www.Spring.security.filter.CustomAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private CustomAuthenticationFilter customAuthenticationFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.addFilterAfter(customAuthenticationFilter, BasicAuthenticationFilter.class)
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.build();
	}
}
