package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

/**
 * Classe de configuração para o perfil de Teste.
 * Classe de configuração que não está presente nas outras camadas, representa uma classe auxiliar
 * que irá realizar algumas configurações em minha aplicação.
 */

@Configuration
@Profile("test") // Configuração específica para o perfil de 'test' //
public class TestConfig implements CommandLineRunner {
	
	// Injeção de dependência //
	@Autowired
	private UserRepository userRepository;

	// Tudo que estiver dentro desse método será executado quando a aplicação
	// for iniciada //
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll( Arrays.asList(u1, u2) );
	}

}
