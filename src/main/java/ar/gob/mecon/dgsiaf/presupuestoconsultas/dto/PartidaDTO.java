package ar.gob.mecon.dgsiaf.presupuestoconsultas.dto;

import java.math.BigDecimal;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;

public class PartidaDTO {

	private Long id;

	private String objetoGasto;

	private String saf;

	private Integer ejercicio;

	private BigDecimal montoInicial;

    public PartidaDTO(Partida unaPartida){
        this.id = unaPartida.getId();
        this.objetoGasto = unaPartida.getObjetoGasto();
        this.saf = unaPartida.getSaf();
        this.ejercicio = unaPartida.getEjercicio();
        this.montoInicial = unaPartida.getMontoInicial();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getObjetoGasto() {
        return objetoGasto;
    }

    public void setObjetoGasto(String objetoGasto) {
        this.objetoGasto = objetoGasto;
    }

    public String getSaf() {
        return saf;
    }

    public void setSaf(String saf) {
        this.saf = saf;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }
}
