package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.UserRepository;

//Registrando como um componente do Spring ou @Component //
@Service // Tem uma semântica melhor, indicando que é um camada de negócio que estende @Component //
public class CategoryService {

	@Autowired // Injeção de dependência //
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
