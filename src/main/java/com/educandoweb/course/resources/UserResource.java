package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

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

	@Autowired // Injeção de Dependência //
	private UserService userService;
	
	// Crio um endPoint para acessar usuários //
	// Responde a requisição tipo GET do http //
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> listUser = userService.findAll();
		return ResponseEntity.ok().body(listUser);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
