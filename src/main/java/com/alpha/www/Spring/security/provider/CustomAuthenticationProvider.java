package com.alpha.www.Spring.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.alpha.www.Spring.security.authentication.CustomAuthentication;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Value("${secret.key}")
	private String secretKey;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
		String headerKey = customAuthentication.getKey();
		
		if (secretKey.equalsIgnoreCase(headerKey)) {
			log.info("Request have valid secret key");
			return new CustomAuthentication(true, headerKey);
		}
		
		throw new BadCredentialsException("Secret key in header is invalid");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthentication.class.equals(authentication);
	}

}
