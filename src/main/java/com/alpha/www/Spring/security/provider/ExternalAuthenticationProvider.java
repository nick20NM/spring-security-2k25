package com.alpha.www.Spring.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.alpha.www.Spring.security.authentication.CustomAuthentication;
import com.alpha.www.Spring.security.authentication.ExternalAuthentication;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExternalAuthenticationProvider implements AuthenticationProvider {

	@Value("${external.secret.key}")
	private String externalSecretKey;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		ExternalAuthentication externalAuthentication = (ExternalAuthentication) authentication;
		String headerKey = externalAuthentication.getKey();
		
		if (externalSecretKey.equalsIgnoreCase(headerKey)) {
			log.info("Request have valid external secret key");
			return new CustomAuthentication(true, headerKey);
		}
		
		throw new BadCredentialsException("External secret key in header is invalid");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ExternalAuthentication.class.equals(authentication);
	}

}
