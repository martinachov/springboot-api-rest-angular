package ar.gob.mecon.dgsiaf.presupuestoconsultas.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.PartidaDTO;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.error.PartidaNotFoundException;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.model.Partida;
import ar.gob.mecon.dgsiaf.presupuestoconsultas.service.impl.PartidaService;

@RestController
public class PartidaController {
	
	@Autowired
	private PartidaService partidaService;

	@GetMapping("/api/partidas")
	public List<PartidaDTO> findAll() {
		return partidaService.findAll().stream()
			.map(PartidaDTO::new)
			.collect(Collectors.toList());	
	}

	@GetMapping("/api/partidas/{partidaId}")
	public PartidaDTO findById(@PathVariable Long partidaId) {
		return new PartidaDTO(partidaService.findById(partidaId).orElseThrow(() -> new PartidaNotFoundException(partidaId)));
	}

	@PostMapping("/api/partidas")
	@ResponseStatus(HttpStatus.CREATED)
	public PartidaDTO create(@Valid @RequestBody Partida partida ) {
		return new PartidaDTO(partidaService.create(partida));
	}
}
