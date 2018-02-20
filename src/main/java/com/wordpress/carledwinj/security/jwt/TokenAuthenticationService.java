package com.wordpress.carledwinj.security.jwt;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	private static final long EXPIRTION_TIME = 300_000;//5 min
	private static final String SECRET = "CarlEdwinTI";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	public static void addAuthentication(HttpServletResponse response, String username) {
		
		String token = Jwts
						.builder()
							.setSubject(username)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRTION_TIME))
							.signWith(SignatureAlgorithm.HS512, SECRET)
							.compact();
		
		response.addHeader("HEADER_STRING", TOKEN_PREFIX.concat(" ").concat(token));
	}
	
	public static Authentication isValidToken(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null && token.startsWith(TOKEN_PREFIX)) {
			
			String tokenParse = Jwts
								   .parser()
								   		.setSigningKey(SECRET)
								   		.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
								   		.getBody()
								   		.getSubject();
			
			if(tokenParse != null) {
				return new UsernamePasswordAuthenticationToken(tokenParse, null, Collections.emptyList());
			}
		}
		
		return null;
	}
	
}
