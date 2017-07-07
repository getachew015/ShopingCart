package org.dagim.shoppingCart.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyDBAuthenticationService myDBAuthenticationService;

	public WebSecurityConfig() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myDBAuthenticationService);
	}
	protected void configure(HttpSecurity http)throws Exception{
		http.csrf().disable();
	
	//The pages require login as Employee or Manager and if no login it will redirect to Login page
	http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
    .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
	//For Managers only
	http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_MANAGER')");
	//When the User is logged in as XX but tries to access a page that requires role YY throws access denied exception
	http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	//Config for Login FOrm
	http.authorizeRequests().and().formLogin()
    // Submit URL of login page. Specifies the URL to validate the credentials.
    .loginProcessingUrl("/j_spring_security_check") 
    // Submit URL Specifies the URL to send users to if login is required. 
    // If used with WebSecurityConfigurerAdapter a default login page will be generated when this attribute is not specified. 
    .loginPage("/login")
    // Specifies where users will go after authenticating successfully if they have not visited a secured page prior to authenticating. 
    // This is a shortcut for calling defaultSuccessUrl(String).
    .defaultSuccessUrl("/accountInfo")
    // The URL to send users if authentication fails. This is a shortcut for invoking failureHandler(AuthenticationFailureHandler). 
    // The default is "/login?error".
    .failureUrl("/login?error=true")
    // The HTTP parameter to look for the username when performing authentication. Default is "username".
    .usernameParameter("userName")
    // The HTTP parameter to look for the password when performing authentication. Default is "password".
    .passwordParameter("password")
    // Config for Logout Page
    // (Go to home page).
    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	
	}
}
