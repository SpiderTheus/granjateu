package com.spider.granjateu.controllers.excptions;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spider.granjateu.controllers.InsumoController;
import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.services.InsumoService;

import com.spider.granjateu.enums.AveStatus;




@ExtendWith(MockitoExtension.class)
class InsumoControllerTest {

	private MockMvc mockMvc;

	@Mock
	private InsumoService insumoService;

	@InjectMocks
	private InsumoController insumoController;

	@BeforeEach
	 void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(insumoController).build();
	}

	@Test
	void deveRetornarInsumoById() throws Exception {

		Insumo insumo = new Insumo();
		insumo.setId(1L);

		when(insumoService.findById(1L)).thenReturn(insumo);

		mockMvc.perform(get("/insumos/{id}", 1L)).
		   andExpect(status().isOk()).
		   andExpect(jsonPath("$.id").value(1L));


	}

	@Test
	void update() throws Exception {
		Long id = 1L;
		Insumo insumo = new Insumo();
		insumo.setId(id);
		insumo.setTipo(AveStatus.CRIA);

		when(insumoService.update(eq(id), any(Insumo.class))).thenReturn(insumo);

		mockMvc.perform(put("/insumos/{id}", id)
				.contentType("application/json")
				.content("{\"tipo\": \"CRIA\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.tipo").value("CRIA")); 
	}

	@Test
	void create() throws Exception {
		Long id = 1L;
		Insumo insumo = new Insumo(AveStatus.CRIA, 100.0, 50.0);
		insumo.setId(id);
		
		when(insumoService.createInsumo(any(Insumo.class))).thenReturn(insumo);

		mockMvc.perform(MockMvcRequestBuilders.post("/insumos")
				.contentType("application/json")
				.content("""
						{
							"tipo": "CRIA",
							"quantidade": 100.0,
							"valor": 50.0
						}
						"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id));

	}

}
