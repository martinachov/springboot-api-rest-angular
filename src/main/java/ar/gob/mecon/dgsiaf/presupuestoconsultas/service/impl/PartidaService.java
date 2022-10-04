package ar.gob.mecon.dgsiaf.presupuestoconsultas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.error.PartidaAlreadyExistsException;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.error.PartidaNotFoundException;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.repository.PartidaRepository;


@Component
@Transactional
public class PartidaService {

	private static final Logger log = LoggerFactory.getLogger(PartidaService.class);

	@Autowired
	PartidaRepository partidaRepository;

	public List<Partida> findAll() {
		return partidaRepository.findAll();
	}

	public Optional<Partida> findById(Long partidaId) {
		return partidaRepository.findById(partidaId);
	}

	public Partida create(Partida partida) {
		if (partidaRepository.findByObjetoGastoAndSafAndEjercicio(partida.getObjetoGasto(), partida.getSaf(), partida.getEjercicio()).isPresent())
			throw new PartidaAlreadyExistsException(partida);
		//TODO Revisar como se crean las partidas. 
		return partidaRepository.save(partida);
	}
	
	public BigDecimal getSaldo(Long idPartida) {
		Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new PartidaNotFoundException(idPartida));
		double montoTotalDeMovimientos = partida.getMovimientos().stream().mapToDouble(m-> m.getMonto().doubleValue()).sum();
		double saldo = partida.getMontoInicial().doubleValue()-montoTotalDeMovimientos;
		log.debug("Monto Partida={0}. Monto de Movimientos={1}. Saldo={3}",partida.getMontoInicial().doubleValue(), montoTotalDeMovimientos, saldo);

		return BigDecimal.valueOf(saldo);
	}
}
