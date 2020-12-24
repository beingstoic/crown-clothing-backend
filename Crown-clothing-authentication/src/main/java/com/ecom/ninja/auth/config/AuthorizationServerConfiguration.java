package com.ecom.ninja.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
/*
 * We are extending AuthorizationServerConfigurerAdapter in order to configure the authorization server
 * so that It can act in a way we want it to act.
 */public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
//	Overriding the authorization server security method
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
		/*
		 * For now we are allowing everyone to get the access token and we are checking that 
		 * the user should be authenticated i.e. token is valid or not
		 */		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
	
	
//	We are overriding the config adapter containing client details
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		/*
		 * With the client id, the user will be able to get access to the server, setting up all the things to basic values for the base,
		 * later on will change the code as per the requirment
		 */		clients
				.inMemory()
				.withClient("ClientId")
				.secret("secret")
				.authorizedGrantTypes("authorization_code")
				.scopes("user_info")
				.autoApprove(true);
	}
	
	
//	Overriding the endpoint configurer
	public void configure(AuthorizationServerEndpointsConfigurer endpoint) {
		endpoint.authenticationManager(authenticationManager);
	}
}
