package ar.gob.mecon.dgsiaf.presupuestoconsultas.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(generator="movimiento_sequence")
    private Long id;

    @ManyToOne
    private Partida partida;

    private BigDecimal monto;

    private String comprobante;

    public Movimiento() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    public Movimiento(Partida partida, BigDecimal monto, String comprobante) {
        setPartida(partida);
        setMonto(monto);
        setComprobante(comprobante);
    }

    @Override
    public String toString() {
        return "Movimiento [partida=" + partida + ", monto=" + monto + ", comprobante=" + comprobante + "]";
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param partida the partida to set
     */
    public Movimiento setPartida(Partida partida) {
        this.partida = partida;
        return this;
    }

    /**
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public Movimiento setMonto(BigDecimal monto) {
        this.monto = monto;
        return this;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public Movimiento setComprobante(String comprobante) {
        this.comprobante = comprobante;
        return this;
    }
}
