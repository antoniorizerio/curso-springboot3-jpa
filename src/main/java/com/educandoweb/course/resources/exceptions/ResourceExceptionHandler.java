package com.educandoweb.course.resources.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.educandoweb.course.services.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/** Tratamento manual para nossos erros.
*   Intercepta as exceções que ocorrerem para que esse objeto possa executar o possível
    tratamento.
    **Essa anotação é do Spring Framework e é utilizada para lidar com exceções 
    *lançadas em qualquer lugar da sua aplicação, não só pelo controller.
*/

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException error, 
			HttpServletRequest request) {
		
		String msgError = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(), status.value(), msgError, 
				error.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, 
			HttpStatusCode status, WebRequest request) { 

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage() )
                .collect(Collectors.toList());
        
        StringBuffer strgBufferMsgError = new StringBuffer();
        
        for(String msgErro : errors) {
        	strgBufferMsgError.append(msgErro);
        	strgBufferMsgError.append(" // ");
        }

        StandardError standardError = new StandardError(Instant.now(), status.value(), strgBufferMsgError.toString(), 
        		ex.getTitleMessageCode(), request.getContextPath());

        return ResponseEntity.status(status).body(standardError);
    }
}
