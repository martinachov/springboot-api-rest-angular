package ar.gob.mecon.dgsiaf.presupuestoconsultas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;


public interface PartidaRepository extends JpaRepository<Partida, Long>{

	public Optional<Partida> findByObjetoGastoAndSafAndEjercicio(String objetoGasto, String saf, Integer ejercicio);
}
