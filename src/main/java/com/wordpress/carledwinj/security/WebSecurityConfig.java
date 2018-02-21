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

	private static final String URL_NOVO_TOKEN = "/novo-token";
	
	private static final String[] URLS_PERMIT_ALL = {"/", "/home", "/perfil/**", "/role/**", "/h2-console/**"};
	
	private static final String QUERY_CONSULTA_USUARIO_ATIVO = "SELECT username, password, enabled FROM usuario WHERE username=?";
	
	private static final String QUERY_CONSULTA_ROLES_USUARIO = "SELECT u.username, r.role FROM ROLE_PERFIS pr " + 
															   " INNER JOIN PERFIL p ON pr.PERFIS_ID = p.ID INNER JOIN USUARIO u ON u.PERFIL_ID = p.ID " + 
															   " INNER JOIN ROLE r ON pr.ROLES_ID = r.id WHERE u.username =?";
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	
		httpSecurity
					.authorizeRequests().antMatchers(URLS_PERMIT_ALL).permitAll()
					.and().authorizeRequests().antMatchers(HttpMethod.POST, URL_NOVO_TOKEN).permitAll()
					.anyRequest().authenticated()
					.and().addFilterBefore(new JWTLoginFilter(URL_NOVO_TOKEN, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
						  .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
				
		 httpSecurity.csrf().disable();
		 httpSecurity.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(QUERY_CONSULTA_USUARIO_ATIVO)
			.authoritiesByUsernameQuery(QUERY_CONSULTA_ROLES_USUARIO);
	}
}
