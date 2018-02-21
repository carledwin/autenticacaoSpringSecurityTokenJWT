package com.wordpress.carledwinj.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinj.model.Usuario;
import com.wordpress.carledwinj.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/todos")
	@ResponseBody
	public ResponseEntity todos() {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		
		if(usuarios != null) {
			return new ResponseEntity<Iterable<Usuario>>(usuarios, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Nenhum registro encontrado para a pesquisa.", HttpStatus.OK); 
	}
}
