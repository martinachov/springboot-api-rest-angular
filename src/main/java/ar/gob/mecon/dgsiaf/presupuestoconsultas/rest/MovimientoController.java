package ar.gob.mecon.dgsiaf.presupuestoconsultas.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.MovimientoDTO;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.MovimientoRequest;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Movimiento;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.service.impl.MovimientoServiceImpl;

@RestController
public class MovimientoController {
    
    @Autowired
    private MovimientoServiceImpl movimientoService;

    // GET /api/partidas/1/movimientos
    // curl http://localhost:8080/api/partidas/1/movimientos 
    @GetMapping("/api/partidas/{partidaId}/movimientos")
    public List<MovimientoDTO> findByPartida(@PathVariable Long partidaId){
        return movimientoService.findByPartida(partidaId).stream().//
        map(MovimientoDTO::new).//
        collect(toList());
    }

    //  curl -X POST "http://localhost:8080/api/movimientos" -d '{"partida":{"id":1,"objetoGasto":"3.4.1.0","saf":"313","ejercicio":2022,"monto":88.00},"monto":10000.00,"comprobante":"2022-OCA-313-1"}' -H 'Content-type:application/json'| jq
    @PostMapping("/api/movimientos")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoDTO create(@RequestBody MovimientoRequest movimiento) { 
        Movimiento movimientoNuevo = movimientoService.save(movimiento);
        return new MovimientoDTO(movimientoNuevo);
    }
}
