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
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.spider.granjateu.dtos.InsumoDto;
import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.InsumoRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class InsumoServiceTest {
	
	@Mock
	private InsumoRepository insumoRepository;

	@InjectMocks
	private InsumoService insumoService;


	@Test
	@DisplayName("Deve retornar uma lista de insumos para um lote de aves específico")
	void DeveRetornarListaInsumosComIdLoteAves() {
		Long id = 1L;

		LoteAves loteAves = new LoteAves();
		loteAves.setId(id);

		Insumo insumo1 = new Insumo();
		insumo1.setLoteAves(loteAves);
		Insumo insumo2 = new Insumo();
		insumo2.setLoteAves(loteAves);

		List<Insumo> insumos = List.of(insumo1, insumo2);

	 Mockito.when(insumoRepository.findByLoteAvesId(id)).thenReturn(insumos);

		List<InsumoDto> result = insumoService.findByLotesDto(id);
		
		assertNotNull(result);
		assertEquals(2, result.size());
		
		Mockito.verify(insumoRepository).findByLoteAvesId(id);

	}

	@Test
	@DisplayName("deve Retornar execption NotFoundException quando não encontrar insumos para o lote de aves")
	void DeveRetornarExceptionNotFoudQuandoListaForVazia() {
		Long id = 1L;


		List<InsumoDto> actual = insumoService.findByLotesDto(id);

		assertNotNull(actual);
		assertTrue(actual.isEmpty());	
		assertThrows(NotFoundException.class, () -> insumoService.findByLoteAvesId(id));

	}


}
