package com.spider.granjateu.entities;

import com.spider.granjateu.enums.AveStatus;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LoteAvesTest {
	@Test
	void LoteAves() {
		LoteAves lote = new LoteAves("Pintinho",  100, 1000,  LocalDate.now(),  AveStatus.CRIA);

		assertEquals("Pintinho", lote.getRaca());
		assertEquals(100, lote.getQuantidade());
		assertEquals(AveStatus.CRIA, lote.getStatus());
	}

	@Test
	void LoteAvesId() {
		LoteAves lote = new LoteAves("Pintinho", 100, 1000, LocalDate.of(2026, 3, 5),  AveStatus.CRIA);

		Long id = lote.getId();
		assertNull(id);
	}

	@Test
	void subtrairQuantidadeBaixas() {
		LoteAves l = new LoteAves("abc", 123, 123.4D, LocalDate.of(2023, 1, 1), AveStatus.RECRIA);
		int baixas = 123;
		l.subtrairQuantidadeBaixas(baixas);

		assertEquals(0, l.getQuantidade());
	}

	@Test
	 void calcularIdadeEmSemanas() {
		LoteAves l = new LoteAves("abc", 123, 123.4D, LocalDate.of(2026, 03, 1), AveStatus.RECRIA);
		int expected = 4;
		int actual = l.calcularIdadeEmSemanas();

		assertEquals(expected, actual);
	}
}
