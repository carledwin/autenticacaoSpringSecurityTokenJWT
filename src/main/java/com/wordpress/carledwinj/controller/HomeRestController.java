package com.wordpress.carledwinj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/home")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/home-acesso-restrito")
	public String homeAcessoRestrito() {
		return "HomeAcessoRestrito";
	}
}
