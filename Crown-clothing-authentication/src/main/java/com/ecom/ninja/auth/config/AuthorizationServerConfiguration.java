package com.ecom.ninja.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
/*
 * We are extending AuthorizationServerConfigurerAdapter in order to configure the authorization server
 * so that It can act in a way we want it to act.
 */public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
		super.configure(security);
	}
}
