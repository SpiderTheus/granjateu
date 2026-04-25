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

@ExtendWith(MockitoExtension.class)
class ManejoSemanalServiceTest {

	@Mock
	private ManejoSemanalRepository manejoSemanalRepository;

	@InjectMocks
	private ManejoSemanalService manejoSemanalService;

	@Test
	@DisplayName("Deve retornar uma lista de ManejoSemanal quando os parâmetros forem válidos")
	void findByDataBetween() {

		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<ManejoSemanal> manejos = new ArrayList<>();
		ManejoSemanal manejo = new ManejoSemanal();
		manejos.add(manejo);

		Mockito.when(manejoSemanalRepository.findByDataBetween(dataInicial, dataFinal)).thenReturn(manejos);

		List<ManejoSemanal> result = manejoSemanalService.findByDataBetween(dataInicial, dataFinal);

		assertNotNull(result);
		assertEquals(1, result.size());
		Mockito.verify(manejoSemanalRepository).findByDataBetween(dataInicial, dataFinal);
	}

}
