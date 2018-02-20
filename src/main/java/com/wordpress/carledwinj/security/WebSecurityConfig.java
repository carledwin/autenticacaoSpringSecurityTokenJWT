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
		
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM usuario WHERE username=?")
				.authoritiesByUsernameQuery("SELECT u.username, r.role FROM PERFIL_ROLES pr\n" + 
						"INNER JOIN PERFIL p ON pr.PERFIS_ID = p.ID \n" + 
						"INNER JOIN USUARIO u ON u.PERFIL_ID = p.ID\n" + 
						"INNER JOIN ROLE r ON pr.ROLES_ID = r.id\n" + 
						"WHERE u.username =?");
	}
}
