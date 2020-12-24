package com.ecom.ninja.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//The first annotation enables this class to act as a resource server and
//the second one is basically asking the spring boot to automatically load the class
@EnableResourceServer
@Configuration
/*
 web security configuration adapter will help us to use spring security and configure it
 */

public class ResourceServerConfig extends WebSecurityConfigurerAdapter{

	
	/*
	 * Provides the authenticate method for authentication, returns true is authenticated, false if
	 * input is invalid and null when it can not decide
	 */	@Autowired
	private AuthenticationManager authenticationManager;
//	We are overriding the configure method which accepts the http security
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		/*
			requestMatchers allows us to match the url along with antmatchers
			We are basically matching the urls and then asking the spring security
			to authorize any kind of request and then authenticate them. After authentication
			they will be allowed to access the form-login
		 */		http.requestMatchers()
			.antMatchers("/login", "/oath/authorization")
			.and()
			.authorizeRequests()
		 	.anyRequest()
		 	.authenticated()
		 	.and()
		 	.formLogin()
		 	.permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		super.configure(auth);
		/*
		 * authenticating it with the parent authentication manager and doing the inMemory authentication
		 * for simplicity, later on we gonna change this up to database authentication
		 */		auth.parentAuthenticationManager(authenticationManager)
		 		.inMemoryAuthentication()
		 		.withUser("Peter")
		 		.password("peter")
		 		.roles("ADMIN");
	}
	
}
