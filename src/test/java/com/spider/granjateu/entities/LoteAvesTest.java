package com.spider.granjateu.entities;

import com.spider.granjateu.enums.AveStatus;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LoteAvesTest {
	@Test
	void LoteAves() {
		LoteAves lote = new LoteAves("Pintinho", 100, LocalDate.now(), 0.5, AveStatus.INICIAL);
	
		assertEquals("Pintinho", lote.getRaca());
		assertEquals(100, lote.getQuantidade());
		assertEquals(LocalDate.now(), lote.getDataDeChegada());
		assertEquals(0.5, lote.getPesoMedio());
		assertEquals(AveStatus.INICIAL, lote.getStatus());
	}

	@Test
	void LoteAvesId() {
		LoteAves lote = new LoteAves("Pintinho", 100, LocalDate.now(), 0.5, AveStatus.INICIAL);
		
		Long id = lote.getId();
		assertNull(id);
	}


}
