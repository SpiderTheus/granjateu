package com.spider.granjateu.controllers.excptions;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.services.InsumoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/insumos")
public class InsumoController {

  InsumoService insumoService;

  public InsumoController(InsumoService insumoService) {
    this.insumoService = insumoService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Insumo> findById(@PathVariable Long id) {
    Insumo insumo = insumoService.findById(id);
    return ResponseEntity.ok(insumo);
  }

  @GetMapping("/tipo")
  public ResponseEntity<List<Insumo>> findByTipo(@RequestParam String tipo) {
    var insumos = insumoService.findByTipoString(tipo);

    if (insumos.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(insumos);
  }

}
