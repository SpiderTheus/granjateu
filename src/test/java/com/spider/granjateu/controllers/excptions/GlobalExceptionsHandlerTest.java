package com.spider.granjateu.controllers.excptions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spider.granjateu.controllers.LoteAvesController;
import com.spider.granjateu.services.exceptions.NotFoundException;



@ExtendWith(MockitoExtension.class)
class GlobalExceptionsHandlerTest {

	private MockMvc mockMvc;

		@Mock
		private LoteAvesController loteAvesController;
	
		@InjectMocks
		private GlobalExceptionsHandler globalExceptionsHandler;
		
		@BeforeEach
		void setup() {
			this.mockMvc = MockMvcBuilders.standaloneSetup(loteAvesController)
			.setControllerAdvice(globalExceptionsHandler)
			.build();
	}


	@Test
	void handleResourceNotFoundException() throws Exception {
		Long id = 1L;
		
		when(loteAvesController.getById(id)).thenThrow(new NotFoundException("Não encontrado LoteAves with id: " + id));

		mockMvc.perform(MockMvcRequestBuilders.get("/lote-aves/{id}", id))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Resource Not Found"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("/lote-aves/" + id))
				.andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("Não encontrado LoteAves with id: " + id));
	}
}
