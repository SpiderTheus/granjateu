package com.spider.granjateu.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spider.granjateu.dtos.LoteAvesDto;
import com.spider.granjateu.services.LoteAvesService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/lote-aves")
public class LoteAvesController {

  LoteAvesService loteAvesService;

  public LoteAvesController(LoteAvesService loteAvesService) {
    this.loteAvesService = loteAvesService;
  }

  @GetMapping("/status")
  public ResponseEntity<List<LoteAvesDto>> getLotesByStatus(@RequestParam String status) {
    var lotesAves = loteAvesService.findByStatus(status);    

     return ResponseEntity.ok(lotesAves);

  }

  @GetMapping("/raca")
  public ResponseEntity<List<LoteAvesDto>> getLotesByRaca(@RequestParam String raca) {
    var lotesAves = loteAvesService.findByRaca(raca);  
    
     return ResponseEntity.ok(lotesAves);
  }

  @GetMapping
  public ResponseEntity<List<LoteAvesDto>> getAll() {
    var lotesAves = loteAvesService.findAll();

     return ResponseEntity.ok(lotesAves);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LoteAvesDto> getById(@PathVariable Long id) {
    var loteAves = loteAvesService.findByIdDto(id);  
    
     return ResponseEntity.ok(loteAves);
  }

  @PutMapping("/{id}")
  public ResponseEntity<LoteAvesDto> putLoteAves(@PathVariable Long id, @RequestBody LoteAvesDto novoLoteAves) {
      var loteAves = loteAvesService.update(id, novoLoteAves);

      return ResponseEntity.ok(loteAves);
  }

  @PostMapping()
  public ResponseEntity<LoteAvesDto> postMethodName( @RequestBody LoteAvesDto novoLoteAves) {
    var loteAves = loteAvesService.create(novoLoteAves);
      
      return ResponseEntity.ok(loteAves);
  } 
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLoteAves(@PathVariable Long id) {
      loteAvesService.delete(id);
      
      return ResponseEntity.noContent().build();

  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<LoteAvesDto> patchStatusLoteAves(@PathVariable Long id, @RequestParam String status) {
      var loteAvesDto = loteAvesService.updateStatus(id, status);  

      return ResponseEntity.ok(loteAvesDto); 
  }

  
}
