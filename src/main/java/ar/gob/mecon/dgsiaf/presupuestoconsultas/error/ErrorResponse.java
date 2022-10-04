package ar.gob.mecon.dgsiaf.presupuestoconsultas.error;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private int status;
	
	private String exception;
	
	private List<String> mensajes;
	
	public ErrorResponse(HttpStatus status, List<String> mensajes, String exception) {
		this.status = status.value();
		this.exception = exception;
		this.mensajes = mensajes;
	}

	public int getStatus() {
		return status;
	}

	public String getException() {
		return exception;
	}

	public List<String> getMensajes() {
		return mensajes;
	}
}
