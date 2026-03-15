package com.spider.granjateu.entities;

import com.spider.granjateu.enums.AveStatus;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class InsumoTest {
	@Test
	void Insumo() {
		long id = 1L;

		LoteAves loteAves = new LoteAves("abc", 123, 123.4D,  LocalDate.of(2023, 1, 1), 123.4D, AveStatus.EM_CRESCIMENTO);
		AveStatus tipo = AveStatus.EM_CRESCIMENTO;
		Double quantidade = 123.4D;
		Double valor = 123.4D;
		loteAves.setId(id);

		
		Insumo actual = new Insumo(loteAves, tipo, quantidade, valor);

		assertEquals(id, actual.getLoteAves().getId());
	}
}
