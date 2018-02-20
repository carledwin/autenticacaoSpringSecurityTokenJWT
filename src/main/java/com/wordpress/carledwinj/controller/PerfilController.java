package com.wordpress.carledwinj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinj.model.Perfil;
import com.wordpress.carledwinj.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping("/todos")
	public ResponseEntity<?> todos() {
		return new ResponseEntity<Iterable<Perfil>>(perfilRepository.findAll(), HttpStatus.OK);
	}
}
