package ar.gob.mecon.dgsiaf.presupuestoconsultas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimientoRequest implements Serializable{
     
    private BigDecimal monto;

    private String comprobante;

    private Long partidaId;

    protected MovimientoRequest(){
    }

    public MovimientoRequest(BigDecimal monto, String comprobante, Long partidaId) {
        this.monto = monto;
        this.comprobante = comprobante;
        this.partidaId = partidaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

}
