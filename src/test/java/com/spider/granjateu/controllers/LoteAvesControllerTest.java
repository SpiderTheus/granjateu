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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
		loteAves.setStatus(AveStatus.CRIA);

		when(loteAvesService.findByStatus("CRIA")).thenReturn(List.of(new LoteAvesDto(loteAves)));

		mockMvc.perform(get("/lote-aves/status").
		   param("status", "CRIA").
		   contentType(MediaType.APPLICATION_JSON)).
		   andExpect(status().isOk()).
		   andExpect(content().contentType(MediaType.APPLICATION_JSON)).
		   andExpect(jsonPath("$[0].quantidade").value(100)).
		   andExpect(jsonPath("$[0].status").value("CRIA"));
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

		when(loteAvesService.update(eq(id),any(LoteAvesDto.class))).
		           thenReturn(novoLoteAves);


		 mockMvc.perform(put("/lote-aves/1").
		      contentType(MediaType.APPLICATION_JSON).
		      content("""
									   {
										"raca": "racaAtualizada",
                    "quantidade": 10
                    
               			 }
									""")).
		      andExpect(status().isOk()).
		      andExpect(jsonPath("$.raca").value("racaAtualizada")).
		            andExpect(jsonPath("$.quantidade").value(10));
	}

	@Test
	void postLoteAves() throws Exception {
		LoteAvesDto novoLoteAves = new LoteAvesDto();
		novoLoteAves.setRaca("raca1");
		novoLoteAves.setQuantidade(100);

		when(loteAvesService.create(any(LoteAvesDto.class))).
		     thenReturn(novoLoteAves);

		mockMvc.perform(MockMvcRequestBuilders.post("/lote-aves").
		     contentType(MediaType.APPLICATION_JSON).
		     content("""
								   {
									"raca": "raca1",
										"quantidade": 100
										
							 			 }
									""")).
		     andExpect(status().isOk()).
		     andExpect(jsonPath("$.raca").value("raca1")).
		      andExpect(jsonPath("$.quantidade").value(100));

	}

	@Test
 	void patchStatusLoteAves() throws Exception {
		Long id = 1L;
		String status = "Postura";

		LoteAves loteAves = new LoteAves();
		loteAves.setId(id);
		loteAves.setRaca("Raca1");
		loteAves.setQuantidade(100);
		loteAves.setDataDeNascimento(java.time.LocalDate.now());
		loteAves.setStatus(AveStatus.CRIA);	

		LoteAvesDto loteAvesAtualizada = new LoteAvesDto(loteAves);
		loteAvesAtualizada.setStatus("POSTURA");


		when(loteAvesService.updateStatus(id, status)).thenReturn(loteAvesAtualizada);

		mockMvc.perform(MockMvcRequestBuilders.patch("/lote-aves/1/status").
		     param("status", "Postura").
		     contentType(MediaType.APPLICATION_JSON)).
		     andExpect(status().isOk()).
		     andExpect(jsonPath("$.status").value("POSTURA"));
	}
}