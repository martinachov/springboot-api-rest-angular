package ar.gob.mecon.dgsiaf.presupuestoconsultas.error;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(PartidaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse partidaNotFound(PartidaNotFoundException ex) {    	
		return new ErrorResponse(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()), ex.getClass().getSimpleName());
	}
    
    @ExceptionHandler(PartidaAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse partidaAlreadyExists(PartidaAlreadyExistsException ex) {    	
		return new ErrorResponse(HttpStatus.CONFLICT, Arrays.asList(ex.getMessage()), ex.getClass().getSimpleName());
	}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse argumentoNotValid(MethodArgumentNotValidException ex) {
    	List<String> errors = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
                
		return new ErrorResponse(HttpStatus.BAD_REQUEST, errors, ex.getClass().getSimpleName());
	}

	@ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse saldoInsuficiente(SaldoInsuficienteException ex) {    	
		return new ErrorResponse(HttpStatus.CONFLICT, Arrays.asList(ex.getMessage()), ex.getClass().getSimpleName());
	}
}
