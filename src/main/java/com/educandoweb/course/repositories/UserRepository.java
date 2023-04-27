package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.educandoweb.course.entities.User;

// Criamos uma implementação padrão para realizar operações com a 
// Entidade User.

// Não precisa da anotação @Repository, pois nossa interface ja estende um componente do Spring: JpaRepository //
@Repository // opcional //
public interface UserRepository extends  JpaRepository<User, Long>{

}
