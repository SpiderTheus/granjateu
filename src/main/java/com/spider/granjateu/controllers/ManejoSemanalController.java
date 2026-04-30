package com.spider.granjateu.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spider.granjateu.dtos.ManejoSemanalDto;
import com.spider.granjateu.entities.ManejoSemanal;
import com.spider.granjateu.services.ManejoSemanalService;

@RestController
@RequestMapping("/manejo")
public class ManejoSemanalController {

  ManejoSemanalService manejoSemanalService;

  public ManejoSemanalController(ManejoSemanalService manejoSemanalService) {
    this.manejoSemanalService = manejoSemanalService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ManejoSemanalDto> findById(@PathVariable Long id) {
    ManejoSemanal manejoSemanal = manejoSemanalService.findById(id);

    return ResponseEntity.ok(new ManejoSemanalDto(manejoSemanal));
  }

  @GetMapping
  public ResponseEntity<List<ManejoSemanalDto>> findAll() {

    return ResponseEntity.ok(manejoSemanalService.findAll());
  }

  @GetMapping("/lote-aves/{loteAvesId}")
  public ResponseEntity<List<ManejoSemanalDto>> findByLoteAvesId(@PathVariable Long loteAvesId) {

    return ResponseEntity.ok(manejoSemanalService.findByLoteAvesId(loteAvesId));
  }

  @GetMapping("/data")
  public ResponseEntity<List<ManejoSemanalDto>> findByDataBetween(@RequestParam String dataInicial, @RequestParam String dataFinal) {
    return ResponseEntity.ok(manejoSemanalService.findByDataBetween(LocalDate.parse(dataInicial), LocalDate.parse(dataFinal)));
  } 

  @PostMapping
  public ResponseEntity<ManejoSemanalDto> create(@RequestBody ManejoSemanalDto manejoSemanalDto) {
    ManejoSemanalDto manejoSemanal = manejoSemanalService.create(manejoSemanalDto);
   
    return ResponseEntity.ok(manejoSemanal);
  } 

  @PutMapping("/{id}")
  public ResponseEntity<ManejoSemanalDto> update(@PathVariable Long id, @RequestBody ManejoSemanalDto manejoSemanalDto) {
    ManejoSemanalDto updatedManejoSemanal = manejoSemanalService.update(id, manejoSemanalDto);  
    
    return ResponseEntity.ok(updatedManejoSemanal);
  }
}
