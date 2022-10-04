package ar.gob.mecon.dgsiaf.presupuestoconsultas.rest;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.gob.mecon.dgsiaf.presupuestoconsultas.dto.MovimientoRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("dev")
public class MovimientoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
    public void should_return_movimientos_from_partida() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/partidas/{partidaId}/movimientos", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comprobante").value("2022-OCA-313-1"));
    }

    @Test
    public void should_return_exception_when_partida_not_found() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/partidas/{partidaId}/movimientos", -1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensajes[0]").value("No existe la partida con id: -1"));
    }

	@Test
	public void should_create_movimiento() throws Exception {
		MovimientoRequest movimientoRequest = new MovimientoRequest(BigDecimal.valueOf(10L), "OC 35",3L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/movimientos")
						.content(objectMapper.writeValueAsString(movimientoRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers
						.jsonPath("$.comprobante").value("OC 35"));

	}

	@Test
	public void should_fail_create_movimiento_partida_not_found() throws Exception {
		MovimientoRequest movimientoRequest = new MovimientoRequest(BigDecimal.valueOf(10L), "OC 35",35L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/movimientos")
						.content(objectMapper.writeValueAsString(movimientoRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andExpect(MockMvcResultMatchers
						.jsonPath("$.mensajes[0]").value("No existe la partida con id: " + 35L))

		;

	}

	@Test
	public void should_fail_create_movimiento_partida_saldo_insuficiente() throws Exception {
		MovimientoRequest movimientoRequest = new MovimientoRequest(BigDecimal.valueOf(5000000L), "OC 35",1L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/movimientos")
						.content(objectMapper.writeValueAsString(movimientoRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isConflict())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("SaldoInsuficienteException"));;

		

	}

}
