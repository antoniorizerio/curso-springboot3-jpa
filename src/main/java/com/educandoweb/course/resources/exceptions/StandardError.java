package com.educandoweb.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Objeto que representa a mensagem de erro que será apresentada para o
 * Usuário.
 * 
 * Camada de resources, pois está na resposta da requisição.
 * @author kakab
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant timestamp;
	
	private Integer status;
	private String error;
	private String message;
	private String path;

}
