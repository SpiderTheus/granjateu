package com.spider.granjateu.controllers;

import com.spider.granjateu.dtos.LoteAvesDto;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.services.LoteAvesService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class LoteAvesControllerTest {


	private MockMvc mockMvc;

	@Mock
	private LoteAvesService loteAvesService;

	@InjectMocks
	private LoteAvesController loteAvesController;

	@BeforeEach
	 void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(loteAvesController).build();
	}

	@Test
	void deveRetornarLoteDOstatus() throws Exception {
		LoteAves loteAves = new LoteAves();
		loteAves.setId(1L);
		loteAves.setQuantidade(100);
		loteAves.setStatus(AveStatus.INICIAL);

		when(loteAvesService.findByStatus("INICIAL")).thenReturn(List.of(new LoteAvesDto(loteAves)));

		mockMvc.perform(get("/lote-aves/status").
		   param("status", "INICIAL").
		   contentType(MediaType.APPLICATION_JSON)).
		   andExpect(status().isOk()).
		   andExpect(content().contentType(MediaType.APPLICATION_JSON)).
		   andExpect(jsonPath("$[0].quantidade").value(100)).
		   andExpect(jsonPath("$[0].status").value("INICIAL"));
	}

	@Test
	void putLoteAves() throws Exception {
		Long id = 1L;

		LoteAves loteAves = new LoteAves();
			loteAves.setId(id);
			loteAves.setRaca("raca1");
			loteAves.setQuantidade(100);

			LoteAvesDto novoLoteAves = new LoteAvesDto();
			novoLoteAves.setRaca("racaAtualizada");
			novoLoteAves.setQuantidade(10);

	when(loteAvesService.update(eq(id), any(LoteAvesDto.class)))
            .thenReturn(novoLoteAves);


			mockMvc.perform(put("/lote-aves/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									   {
										"raca": "racaAtualizada",
                    "quantidade": 10
                    
               			 }
									"""))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.raca").value("racaAtualizada"))
           		.andExpect(jsonPath("$.quantidade").value(10));
	}
}
