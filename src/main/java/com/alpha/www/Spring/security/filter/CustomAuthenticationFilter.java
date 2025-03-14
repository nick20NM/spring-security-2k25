package com.alpha.www.Spring.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alpha.www.Spring.security.authentication.CustomAuthentication;
import com.alpha.www.Spring.security.manager.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

	private CustomAuthenticationManager customAuthenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headerKey = request.getHeader("key");
		CustomAuthentication customAuthentication = new CustomAuthentication(false, headerKey);
		Authentication authenticatedObject = customAuthenticationManager.authenticate(customAuthentication);
		SecurityContextHolder.getContext().setAuthentication(authenticatedObject);
		filterChain.doFilter(request, response);
	}

}
