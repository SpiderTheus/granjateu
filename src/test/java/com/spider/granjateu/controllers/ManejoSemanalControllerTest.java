package com.spider.granjateu.controllers;


import com.spider.granjateu.dtos.ManejoSemanalDto;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.entities.ManejoSemanal;

import com.spider.granjateu.services.ManejoSemanalService;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ManejoSemanalControllerTest {
private MockMvc mockMvc;

	@Mock
	private ManejoSemanalService manejoSemanalService;

	@InjectMocks
	private ManejoSemanalController manejoSemanalController;

	@BeforeEach
	 void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(manejoSemanalController).build();
	}

	@Test
	void putManejoSemanal() throws Exception {
		Long id = 1L;

		LoteAves loteAves = new LoteAves();
		loteAves.setId(1L);

		ManejoSemanal manejoSemanal = new ManejoSemanal();
		 manejoSemanal.setId(id);
		 manejoSemanal.setLoteAves(loteAves);
		 manejoSemanal.setObservacao("observacao");
		 manejoSemanal.setConsumo(5.0);
		 manejoSemanal.setPerdas(2);
		 manejoSemanal.setOvosColetados(100);
		 

		 ManejoSemanalDto novoManejoSemanal = new ManejoSemanalDto();
		 novoManejoSemanal.setObservacao("observacao Atualizada");
		 novoManejoSemanal.setConsumo(10.0);
		 novoManejoSemanal.setPerdas(1);
		 novoManejoSemanal.setOvosColetados(150);

		when(manejoSemanalService.update(eq(id),any(ManejoSemanalDto.class))).
		           thenReturn(novoManejoSemanal);


		 mockMvc.perform(put("/manejo/1").
		      contentType(MediaType.APPLICATION_JSON).
		      content("""
									   {

											"observacao": "observacao Atualizada",
											"consumo": 10.0,
											"perdas": 1,
											"ovosColetados": 150	
                    
               			 }
									""")).
		      andExpect(status().isOk()).
		      andExpect(jsonPath("$.observacao").value("observacao Atualizada")).
		            andExpect(jsonPath("$.consumo").value(10.0)).
		            andExpect(jsonPath("$.perdas").value(1)).
		            andExpect(jsonPath("$.ovosColetados").value(150));
	}

}
