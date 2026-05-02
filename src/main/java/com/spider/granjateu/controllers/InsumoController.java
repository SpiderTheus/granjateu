package com.spider.granjateu.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spider.granjateu.dtos.InsumoDto;
import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.services.InsumoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/insumos")
public class InsumoController {

  InsumoService insumoService;

  public InsumoController(InsumoService insumoService) {
    this.insumoService = insumoService;
  }


  @GetMapping
  public ResponseEntity<List<Insumo>> findAll() {
    List<Insumo> insumos = insumoService.findAll();
    
    return ResponseEntity.ok(insumos);
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
  
  @PutMapping("/{id}")
  public ResponseEntity<Insumo> update(@PathVariable Long id, @RequestBody InsumoDto insumoDto) {
    Insumo updatedInsumo = insumoService.update(id, insumoDto);

    return ResponseEntity.ok(updatedInsumo);  
  }
  
  @PostMapping
  public ResponseEntity<Insumo> create(@RequestBody InsumoDto insumoDto) {
    Insumo createdInsumo = insumoService.createInsumo(insumoDto);

    return ResponseEntity.ok(createdInsumo);
    
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    insumoService.delete(id);
    return ResponseEntity.noContent().build();
  }



}
