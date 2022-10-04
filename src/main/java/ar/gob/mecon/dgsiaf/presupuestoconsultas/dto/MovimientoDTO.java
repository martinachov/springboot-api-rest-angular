package ar.gob.mecon.dgsiaf.presupuestoconsultas.dto;

import java.math.BigDecimal;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Movimiento;

public class MovimientoDTO {

    private BigDecimal monto;

    private String comprobante;

    public MovimientoDTO(Movimiento unMovimiento){
        this.monto = unMovimiento.getMonto();
        this.comprobante = unMovimiento.getComprobante();
    }

    public BigDecimal getMonto() {
        return monto;
    }

   
    public String getComprobante() {
        return comprobante;
    }

   
}
