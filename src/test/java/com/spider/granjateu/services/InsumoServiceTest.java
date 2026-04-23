package com.spider.granjateu.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spider.granjateu.entities.Insumo;

import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.InsumoRepository;
import com.spider.granjateu.services.exceptions.AttributeMandatoryException;


@ExtendWith(MockitoExtension.class)
class InsumoServiceTest {

	@Mock
	private InsumoRepository insumoRepository;

	@InjectMocks
	private InsumoService insumoService;


	@Test
	@DisplayName("Deve retornar uma lista de insumos com um específico")
	void DeveRetornarListaInsumosComTipo() {
		AveStatus tipo = AveStatus.CRIA;

		Insumo insumo = new Insumo();
		insumo.setTipo(tipo);

		Insumo insumo2 = new Insumo();
		insumo2.setTipo(tipo);

		List<Insumo> insumos = List.of(insumo, insumo2);

		Mockito.when(insumoRepository.findByTipo(tipo)).thenReturn(insumos);

		List<Insumo> resultado = insumoService.findByTipo(tipo);

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertTrue(resultado.stream().allMatch(i -> i.getTipo() == tipo));

	}

	@Test
	@DisplayName("Deve lançar exception quando não encontrar insumos com o tipo especificado")
	void createInsumoMandatoryException() {
		Insumo insumo = new Insumo();
	 	
		assertThrows(AttributeMandatoryException.class, () -> insumoService.createInsumo(insumo));
	}




}
