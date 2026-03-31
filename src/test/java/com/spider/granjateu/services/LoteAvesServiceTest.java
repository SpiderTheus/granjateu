package com.spider.granjateu.services;

import com.spider.granjateu.dtos.LoteAvesDto;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.LoteAvesRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

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
		

		List<LoteAvesDto> result = loteAvesService.findByStatus(status);

		assertNotNull(result);
		assertEquals(1, result.size());
		Mockito.verify(loteAvesRepository).findByStatus(AveStatus.INICIAL);
	}

	@Test
	@DisplayName("Deve retornar uma lista vazia quando o status for inválido")
	void findByStatusInvalid() {

		String status = "Invalido";

		List<LoteAvesDto> result = loteAvesService.findByStatus(status);

		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(loteAvesRepository, Mockito.never()).findByStatus(Mockito.any());

	}

	@Test
	@DisplayName("Deve retornar uma lista vazia quando não houver lotes com o status especificado")
	void findByStatusNotFound() {
		String status = "postura";

		Mockito.when(loteAvesRepository.findByStatus(AveStatus.POSTURA)).thenReturn(List.of());

		List<LoteAvesDto> result = loteAvesService.findByStatus(status);

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

	@Test
	@DisplayName("Deve retornar uma lista vazia quando a raça não for encontrada")
	void findByRacaExecption() {
		String raca = "abc";

		List<LoteAvesDto> actual = loteAvesService.findByRaca(raca);

		assertNotNull(actual);
		assertTrue(actual.isEmpty());
		assertThrows(NotFoundException.class, () -> loteAvesService.buscarPorRaca(raca));

	}

	@Test
	void update() {
		Long id = 1L;
		LoteAves loteAves = new LoteAves();
		loteAves.setId(id);
		loteAves.setRaca("Raca1");
		loteAves.setQuantidade(100);
		loteAves.setDataDeNascimento(LocalDate.now());
		loteAves.setStatus(AveStatus.INICIAL);

		Mockito.when(loteAvesRepository.findById(id)).thenReturn(java.util.Optional.of(loteAves));
		Mockito.when(loteAvesRepository.save(Mockito.any(LoteAves.class))).thenReturn(loteAves);

		LoteAvesDto result = loteAvesService.update(id, new LoteAvesDto(loteAves));

		assertNotNull(result);
	
		assertEquals("Raca1", result.getRaca());
		assertEquals(100, result.getQuantidade());
		assertEquals("INICIAL", result.getStatus());

		Mockito.verify(loteAvesRepository).findById(id);
		Mockito.verify(loteAvesRepository).save(Mockito.any(LoteAves.class));

	}

	@Test
	@DisplayName("Deve manter os atributos do lote de aves que não foram atualizados e estejam null ou zero")
	void updateManterAtributo() {
		Long id = 1L;
		LoteAves loteAves = new LoteAves();
		loteAves.setId(id);
		loteAves.setRaca("Raca1");
		loteAves.setQuantidade(100);
		loteAves.setDataDeNascimento(LocalDate.now());
		loteAves.setStatus(AveStatus.INICIAL);

		LoteAvesDto loteAvesAtualizado = new LoteAvesDto();
		loteAvesAtualizado.setRaca(null);
		loteAvesAtualizado.setQuantidade(0);
		loteAvesAtualizado.setDataDeNascimento(LocalDate.now());
		loteAvesAtualizado.setStatus(null);

		Mockito.when(loteAvesRepository.findById(id)).thenReturn(java.util.Optional.of(loteAves));
		Mockito.when(loteAvesRepository.save(Mockito.any(LoteAves.class))).thenReturn(loteAves);

		LoteAvesDto result = loteAvesService.update(id, loteAvesAtualizado);

		assertNotNull(result);
		assertEquals("Raca1", result.getRaca());
		assertEquals(100, result.getQuantidade());
		assertEquals("INICIAL", result.getStatus());

		Mockito.verify(loteAvesRepository).findById(id);
		Mockito.verify(loteAvesRepository).save(Mockito.any(LoteAves.class));

	}

	@Test
	@DisplayName("Deve atualizar o status do lote de aves")
	void updateStatus() {
		Long id = 1L;
		String status = "Postura";

		LoteAves loteAves = new LoteAves();
		loteAves.setId(id);
		loteAves.setRaca("Raca1");
		loteAves.setQuantidade(100);
		loteAves.setDataDeNascimento(LocalDate.now());
		loteAves.setStatus(AveStatus.INICIAL);

		Mockito.when(loteAvesRepository.findById(id)).thenReturn(java.util.Optional.of(loteAves));
		Mockito.when(loteAvesRepository.save(Mockito.any(LoteAves.class))).thenReturn(loteAves);

		LoteAvesDto result = loteAvesService.updateStatus(id, status);

		assertNotNull(result);
		
		assertEquals("Raca1", result.getRaca());
		assertEquals(100, result.getQuantidade());
		assertEquals("POSTURA", result.getStatus());

		Mockito.verify(loteAvesRepository).findById(id);
		Mockito.verify(loteAvesRepository).save(Mockito.any(LoteAves.class));
	}



}
