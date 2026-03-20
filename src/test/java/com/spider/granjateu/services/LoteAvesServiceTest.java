package com.spider.granjateu.services;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.LoteAvesRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoteAvesServiceTest {

	@Mock
	private LoteAvesRepository loteAvesRepository;

	@InjectMocks
	private LoteAvesService loteAvesService;

	@Test
	@DisplayName("Deve retornar uma lista de LoteAves quando o status for válido")
	void findByStatus() {

		String status = "Inicial";
		List<LoteAves> lotes = new ArrayList<>();
		LoteAves lote = new LoteAves();
		lote.setStatus(AveStatus.INICIAL);
		lotes.add(lote);

		Mockito.when(loteAvesRepository.findByStatus(AveStatus.INICIAL)).thenReturn(lotes);

		List<LoteAves> result = loteAvesService.findByStatus(status);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(AveStatus.INICIAL, result.get(0).getStatus());
		Mockito.verify(loteAvesRepository).findByStatus(AveStatus.INICIAL);
	}

	@Test
	@DisplayName("Deve retornar uma lista vazia quando o status for inválido")
	void findByStatusInvalid() {

		String status = "Invalido";
		
		List<LoteAves> result = loteAvesService.findByStatus(status);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(loteAvesRepository, Mockito.never()).findByStatus(Mockito.any());

	}

	@Test
	@DisplayName("Deve retornar uma lista vazia quando não houver lotes com o status especificado")
	void findByStatusNotFound() {
		String status = "postura";

		Mockito.when(loteAvesRepository.findByStatus(AveStatus.POSTURA)).thenReturn(List.of());
		
		List<LoteAves> result = loteAvesService.findByStatus(status);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(loteAvesRepository).findByStatus(AveStatus.POSTURA);
	}

	@Test
	@DisplayName("Deve mudar a string para o enum correspondente")
	void parseStatus() {
		String status = "Inicial";

		AveStatus expected = AveStatus.INICIAL;

		AveStatus actual = loteAvesService.parseStatus(status);

		assertEquals(expected, actual);
		
	}


}
