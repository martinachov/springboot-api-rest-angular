package ar.gob.mecon.dgsiaf.presupuestoconsultas.error;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;

public class PartidaAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -5065667418643242034L;

	public PartidaAlreadyExistsException(Partida partida) {
		super("Ya existe una partida con Objeto Gasto " + partida.getObjetoGasto() + ", SAF " + partida.getSaf() + " y Ejercicio " + partida.getEjercicio());
	}
}
