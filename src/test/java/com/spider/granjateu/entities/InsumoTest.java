package com.spider.granjateu.entities;

import com.spider.granjateu.enums.AveStatus;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class InsumoTest {
	@Test
	void Insumo() {
		long id = 1L;

		AveStatus tipo = AveStatus.RECRIA;
		Double quantidade = 123.4D;
		Double valor = 123.4D;
	
		Insumo actual = new Insumo(tipo, quantidade, valor);
		actual.setId(id);

		assertEquals(id, actual.getId());
		assertEquals(tipo, actual.getTipo());
		assertEquals(quantidade, actual.getQuantidade());	
		assertEquals(valor, actual.getValor());
	}
}
