package com.wordpress.carledwinj.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wordpress.carledwinj.security.jwt.JWTAuthenticationFilter;
import com.wordpress.carledwinj.security.jwt.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/home").permitAll()
				.antMatchers("/h2-console/*").permitAll()
				.antMatchers(HttpMethod.POST, "/novo-token").permitAll()
			.anyRequest().authenticated()
		.and()
			.addFilterBefore(new JWTLoginFilter("/novo-token", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/
		 
		httpSecurity
					.authorizeRequests().antMatchers("/", "/home").permitAll()
				.and()
					.authorizeRequests().antMatchers("/h2-console/**").permitAll()
				.and()
					.authorizeRequests().antMatchers(HttpMethod.POST, "/novo-token").permitAll()
				.anyRequest()
						.authenticated()
					.and()
						.addFilterBefore(new JWTLoginFilter("/novo-token", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
						.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
				
		 httpSecurity.csrf().disable();
		 httpSecurity.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("carl").password("senha123").roles("ADMIN");
		/*auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM usuario WHERE username=? ")
				.authoritiesByUsernameQuery("SELECT username, role FROM perfil_roles WHERE username=? ");*/
	}
}
