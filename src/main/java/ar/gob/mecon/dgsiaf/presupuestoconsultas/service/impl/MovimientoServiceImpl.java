package ar.gob.mecon.dgsiaf.presupuestoconsultas.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.MovimientoRequest;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.error.PartidaNotFoundException;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.error.SaldoInsuficienteException;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Movimiento;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.repository.MovimientoRepository;

@Component
@Transactional
public class MovimientoServiceImpl {
    
    private static final Logger log = LoggerFactory.getLogger(PartidaService.class);
	    
    @Autowired
	PartidaService partidaService;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Movimiento> findByPartida(Long partidaId){
        Partida partida = partidaService.findById(partidaId).orElseThrow(() -> new PartidaNotFoundException(partidaId));
        return movimientoRepository.findByPartidaId(partida.getId());
    }

    public Movimiento save(MovimientoRequest movimientoRequest){
        BigDecimal saldo = partidaService.getSaldo(movimientoRequest.getPartidaId());
        if (saldo.compareTo(movimientoRequest.getMonto()) < 0){
            throw new SaldoInsuficienteException(movimientoRequest,saldo);
        }else{

        Partida partida = partidaService.findById(movimientoRequest.getPartidaId()).orElseThrow(() -> new PartidaNotFoundException(movimientoRequest.getPartidaId()));
        
        Movimiento newMovimiento = new Movimiento(partida, movimientoRequest.getMonto(), movimientoRequest.getComprobante());
        partida.getMovimientos().add(newMovimiento);
        log.debug(movimientoRequest.toString());
        return movimientoRepository.saveAndFlush(newMovimiento);
        }
    }
}
