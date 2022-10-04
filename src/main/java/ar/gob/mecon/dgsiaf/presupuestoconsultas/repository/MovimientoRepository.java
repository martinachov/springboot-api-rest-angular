package ar.gob.mecon.dgsiaf.presupuestoconsultas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Movimiento;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
    
    List<Movimiento> findByPartida(Partida partida);

    List<Movimiento> findByPartidaId(Long partidaId);
}
