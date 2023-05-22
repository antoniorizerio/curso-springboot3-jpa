package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

import jakarta.validation.Valid;

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
	
	/*
	 * Quando inserimos um recurso o retorno correto não é 200 e sim 201 (Novo Recurso Criado).
	 * Usamos @RequestBody: Annotation indicating a method parameter should be bound to the body 
	 * of the web request.
	 * 
	 */
	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody User obj){
		obj = userService.insert(obj);
		//
		// ServletUriComponentsBuilder: Construtor de URI - Uniform Resource Identifier (URI)
		// fromCurrentRequest(): Retorna a Requisição corrente;
		// Coloca um PATH na minha URL: /{id}; e coloca valores nessa minha variável;
		// EX: http:// localhost:8090/users/id/3;
		// E transforma para URI;
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
		//
		// ResponseEntity.created(): Uso o método ResponseEntity.created() 
		// porque estou criando um recurso;

		return ResponseEntity.created(uri).body(obj);
	}

	// endpoint de deleção //
	// @PathVariable: um parâmetro será passado na URL //
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		// noContent(): Pois na responsta não vai nada, retorna um 204.
		return ResponseEntity.noContent().build();
	}
	
	// Atualizar - utilizar o padrão PUT. //
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = userService.update(id, obj);
		// ResponseEntity.ok() - indicando que deu tudo certo - Status 200 //
		return ResponseEntity.ok().body(obj);
	}
	
}
