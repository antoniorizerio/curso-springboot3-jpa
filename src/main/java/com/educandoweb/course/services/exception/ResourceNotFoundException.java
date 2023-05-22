package com.educandoweb.course.services.exception;


/**
 * Exceção personalizada da camada de serviço. Nossa camada de serviço deve ser
 * capaz de lançar exceções dela. E não permitir estourar exceções diversas.
 * 
 * RuntimeException - O compilador não te obriga a tratar.
 * 
 * @author kakab
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// {id}: O id do objeto que tentei encontrar e não encontrei //
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
	
}
