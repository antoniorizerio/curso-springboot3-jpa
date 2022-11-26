package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Configura o projeto para que ele seja uma aplicação do SpringBoot //
@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		// Método para rodar a aplicação //
		SpringApplication.run(CourseApplication.class, args);
	}

}
