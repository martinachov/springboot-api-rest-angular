package ar.gob.mecon.dgsiaf.presupuestoconsultas.data;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Movimiento;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.repository.MovimientoRepository;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.repository.PartidaRepository;

@Component
@Profile("dev")
public class DataInitialization implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DataInitialization.class);

	@Autowired
	private PartidaRepository repository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
	@Override
	public void run(String... args) throws Exception {
		Partida partida1 = new Partida("3.4.1.0", "313", Integer.valueOf(2022),new BigDecimal("88"));
		Partida partida2 = new Partida("2.1.9.0", "101", Integer.valueOf(2021), new BigDecimal("1956.14"));
		Partida partida3 = new Partida("8.7.4.3", "355", Integer.valueOf(2022),new BigDecimal("98516.14"));
		
		repository.save(partida1);
		repository.save(partida2);
		repository.save(partida3);
		
		repository.findAll().forEach(p -> log.info(p.toString()));

        BigDecimal monto1 = new BigDecimal(10000);
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setPartida(partida1).setMonto(monto1).setComprobante("2022-OCA-313-1");
        BigDecimal monto2 = new BigDecimal(20000);
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setPartida(partida2).setMonto(monto2).setComprobante("2022-OCC-101-1");
        BigDecimal monto3 = new BigDecimal(3000);
        Movimiento movimiento3 = new Movimiento();
        movimiento3.setPartida(partida3).setMonto(monto3).setComprobante("2022-OCC-355-2");
        BigDecimal monto4 = new BigDecimal(6456.87);
        Movimiento movimiento4 = new Movimiento();
        movimiento4.setPartida(partida1).setMonto(monto4).setComprobante("2022-OCA-313-4");

        movimientoRepository.save(movimiento1);
        movimientoRepository.save(movimiento2);
        movimientoRepository.save(movimiento3);
        movimientoRepository.save(movimiento4);

        movimientoRepository.findAll().forEach(m -> log.info(m.toString()));
	}
}
