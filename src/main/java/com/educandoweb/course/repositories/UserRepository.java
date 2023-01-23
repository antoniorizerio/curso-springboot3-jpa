package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.User;

// Criamos uma implementação padrão para realizar operações com a 
// Entidade User.
public interface UserRepository extends JpaRepository<User, Long>{

}
