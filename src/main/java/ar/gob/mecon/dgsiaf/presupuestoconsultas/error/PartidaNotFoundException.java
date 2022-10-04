package ar.gob.mecon.dgsiaf.presupuestoconsultas.error;

public class PartidaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5065667416643242034L;

	public PartidaNotFoundException(Long id) {
		super("No existe la partida con id: " + id);
	}
}
