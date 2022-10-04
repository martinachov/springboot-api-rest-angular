package ar.gob.mecon.dgsiaf.presupuestoconsultas.error;

import java.math.BigDecimal;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.MovimientoRequest;

public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = -5065667416643242034L;

	public SaldoInsuficienteException(MovimientoRequest movimiento, BigDecimal saldo) {
		//TODO No es necesario recibir el objeto entero
		super("Saldo insuficiente en la partida " + movimiento.getPartidaId().toString() + ", Saldo:" + saldo.toString());
	}
}
