package ar.gob.mecon.dgsiaf.presupuestoconsultas.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table
public class Partida {

	@Id
	@GeneratedValue(generator="partida_sequence")
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "El objeto de Gasto es obligatorio.")
	private String objetoGasto;

	@Column(nullable = false)
	@NotBlank(message = "El SAF es obligatorio.")
	private String saf;

	@Column(nullable = false)
	@NotNull(message = "El ejercicio es obligatorio.")
	private Integer ejercicio;

	@Positive(message = "El importe debe ser mayor a cero.")
	@NotNull(message = "El monto inicial es obligatorio.")
	private BigDecimal montoInicial;

    @OneToMany(mappedBy = "partida", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();

	protected Partida() {
	}

	public Partida(String objetoGasto, String saf, Integer ejercicio, BigDecimal montoInicial) {
		this.montoInicial = montoInicial;
		this.objetoGasto = objetoGasto;
		this.saf = saf;
		this.ejercicio = ejercicio;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", ejercicio=" + ejercicio + ", saf=" + saf + ", objetoGasto=" + objetoGasto + ", monto=" + montoInicial + "]";
	}

	public Long getId() {
		return id;
	}

	public Partida setId(Long id) {
		this.id = id;
		return this;
	}

	public String getObjetoGasto() {
		return objetoGasto;
	}

	public Partida setObjetoGasto(String objetoGasto) {
		this.objetoGasto = objetoGasto;
		return this;
	}

	public String getSaf() {
		return saf;
	}

	public Partida setSaf(String saf) {
		this.saf = saf;
		return this;
	}

	public Integer getEjercicio() {
		return ejercicio;
	}

	public Partida setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
		return this;
	}

	public BigDecimal getMontoInicial() {
		return montoInicial;
	}

	public Partida setMontoInicial(BigDecimal monto) {
		this.montoInicial = monto;
		return this;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
}
