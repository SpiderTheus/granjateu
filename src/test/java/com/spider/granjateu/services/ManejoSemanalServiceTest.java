package com.spider.granjateu.services;

import com.spider.granjateu.dtos.LoteAvesDto;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.entities.ManejoSemanal;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.LoteAvesRepository;
import com.spider.granjateu.repositories.ManejoSemanalRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import com.spider.granjateu.dtos.ManejoSemanalDto;

@ExtendWith(MockitoExtension.class)
class ManejoSemanalServiceTest {

	@Mock
	private ManejoSemanalRepository manejoSemanalRepository;

	@InjectMocks
	private ManejoSemanalService manejoSemanalService;

	@Mock
	private LoteAvesService loteAvesService;

	@Test
	@DisplayName("Deve retornar uma lista de ManejoSemanal quando os parâmetros forem válidos")
	void findByDataBetween() {

		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<ManejoSemanal> manejos = new ArrayList<>();
		LoteAves loteAves = new LoteAves();
		loteAves.setId(1L);

		ManejoSemanal manejo = new ManejoSemanal();
		manejo.setLoteAves(loteAves);
		manejos.add(manejo);

		Mockito.when(manejoSemanalRepository.findByDataBetween(dataInicial, dataFinal)).thenReturn(manejos);

		List<ManejoSemanalDto> result = manejoSemanalService.findByDataBetween(dataInicial, dataFinal);

		assertNotNull(result);
		assertEquals(1, result.size());
		Mockito.verify(manejoSemanalRepository).findByDataBetween(dataInicial, dataFinal);
	}

	@Test
		void changeToDto() {
		long loteAvesId = 1L;
		List<ManejoSemanal> manejos = new ArrayList<>();
		LoteAves loteAves = new LoteAves();
		loteAves.setId(loteAvesId);
		ManejoSemanal manejo = new ManejoSemanal();
		manejo.setLoteAves(loteAves);
		manejo.setObservacao("Observação de teste");
		manejos.add(manejo);

		List<ManejoSemanalDto> dtos = manejoSemanalService.changeToDto(manejos);	

		assertNotNull(dtos);
		assertEquals(1, dtos.size());
		assertEquals("Observação de teste", dtos.get(0).getObservacao());}
}
