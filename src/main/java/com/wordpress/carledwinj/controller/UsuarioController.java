package com.wordpress.carledwinj.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinj.model.Usuario;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@GetMapping("todos")
	public Iterable<Usuario> todos() {
		return Arrays.asList(new Usuario("Fulano", "fulano", null));
	}
}
