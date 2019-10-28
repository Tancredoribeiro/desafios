package br.com.famintos.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.famintos.config.Constants;
import br.com.famintos.dto.RestauranteDTO;
import br.com.famintos.service.RestauranteService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@WebAppConfiguration
class RestaurenteControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private RestauranteService  restauranteService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	void testFlaharanaCriacaoRestauranteComNomeEmBranco() throws Exception {
		RestauranteDTO dtoMock = new RestauranteDTO();
		dtoMock.setNome(null);
		dtoMock.setEndereco("rua abc, 123");

		mockMvc.perform(post(Constants.URL_CRIAR_RESTAURANTE).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(dtoMock)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void testCriarRestauranteComSucesso() throws Exception {
		RestauranteDTO dtoMock = new RestauranteDTO();
		dtoMock.setNome("blabla");
		dtoMock.setEndereco("rua abc, 123");

		mockMvc.perform(post(Constants.URL_CRIAR_RESTAURANTE).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(dtoMock)))
				.andExpect(status().isOk());
	}

}
