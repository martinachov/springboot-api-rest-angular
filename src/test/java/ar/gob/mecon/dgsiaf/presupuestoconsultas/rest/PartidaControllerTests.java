package ar.gob.mecon.dgsiaf.presupuestoconsultas.rest;

import java.util.Map;

import javax.transaction.Transactional;

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

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("dev")
public class PartidaControllerTests {
    
    @Autowired
	private MockMvc mockMvc;

    @Autowired
	private ObjectMapper objectMapper;

    @Test
    public void should_return_all_partidas() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/partidas"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(3)));
    }

    @Test
    public void should_return_partida_created() throws Exception {
        Map<String, Object> partidaMap = Map.of("objetoGasto", "3.4.1.3", "saf", "311", "ejercicio", "2022", "montoInicial","100" );
        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/api/partidas/")
                .content(objectMapper.writeValueAsString(partidaMap))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.objetoGasto").value("3.4.1.3"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.saf").value("311"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ejercicio").value(2022))
            .andExpect(MockMvcResultMatchers.jsonPath("$.movimientos").isEmpty());
    }

    @Test
    public void should_return_exception_when_partida_not_found() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/partidas/{id}",-1L))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("PartidaNotFoundException"));
    }

    @Test
    public void should_return_exception_when_partida_exists() throws Exception {
        Map<String, Object> partidaMap = Map.of("objetoGasto", "3.4.1.0", "saf", "313", "ejercicio", "2022", "montoInicial","88" );
        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/api/partidas/")
                .content(objectMapper.writeValueAsString(partidaMap))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("PartidaAlreadyExistsException"));
    }

    @Test
    public void should_return_exception_when_arguments_not_valid() throws Exception {
        Map<String, Object> partidaMap = Map.of("montoInicial","-88");

        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/api/partidas/")
                .content(objectMapper.writeValueAsString(partidaMap))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("MethodArgumentNotValidException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensajes", Matchers.hasSize(4)));
    }

    @Test
    public void should_return_one_partida_by_id() throws Exception {
        this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/partidas/{id}",1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.objetoGasto").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.saf").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ejercicio").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.montoInicial").isNotEmpty());
    }
}
