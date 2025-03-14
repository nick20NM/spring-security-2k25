package com.alpha.www.Spring.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.alpha.www.Spring.security.provider.CustomAuthenticationProvider;
import com.alpha.www.Spring.security.provider.ExternalAuthenticationProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

	private CustomAuthenticationProvider customAuthenticationProvider;
	private ExternalAuthenticationProvider externalAuthenticationProvider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		if (customAuthenticationProvider.supports(authentication.getClass())) {
			return customAuthenticationProvider.authenticate(authentication);
		} else {
			return externalAuthenticationProvider.authenticate(authentication);
		}
	}
}
