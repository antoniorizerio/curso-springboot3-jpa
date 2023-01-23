package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.educandoweb.course.entities.User;

/**
 * Representa o Controlador Rest referente a entidade User.
 * Representa uma camada de Recursos, os controladores Rest. Esses controladores dependem
 * da camada de serviço - Service Layer -, que por sua vez depende da camada de acesso a dados
 * Data Access Layer
 * @author Antonio Rizério JR
 *
 */
@RestController
//	**  Nome do meu recurso '/users' //
@RequestMapping(value = "/users") 
public class UserResource {
	
	// Crio um endPoint para acessar usuários //
	// Responde a requisição tipo GET do http //
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "maria@gmail.com", "9999999999", "12345");
		return ResponseEntity.ok().body(u);
	}

}
