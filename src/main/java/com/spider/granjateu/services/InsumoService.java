package com.spider.granjateu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spider.granjateu.dtos.InsumoDto;
import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.InsumoRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;
import com.spider.granjateu.services.exceptions.StatusInvalidException;

@Service
public class InsumoService  {
  private final InsumoRepository insumoRepository;

  public InsumoService(InsumoRepository insumoRepository) {
    this.insumoRepository = insumoRepository;
  }

  public InsumoDto findByIdDto(Long id) {
    Insumo insumo = findById(id);
    return new InsumoDto(insumo);
  }

  public List<InsumoDto> findAll() {
    return findAllDtos(insumoRepository.findAll());
  }

  public List<InsumoDto> findByTipoDto(String tipo) {
    try {
      AveStatus parsedTipo = AveStatus.parseStatus(tipo);

      List<Insumo> insumos = findByTipo(parsedTipo);
      return findAllDtos(insumos);

    } catch (StatusInvalidException | NotFoundException e) {
     return List.of();
    } 
  }


  public List<Insumo> findByTipo(AveStatus tipo) {
    List<Insumo> insumos = insumoRepository.findByTipo(tipo);

    if (insumos.isEmpty()) {
      throw new NotFoundException("Nenhum insumo encontrado para o tipo " + tipo);
    }
    return insumos;
  }


  public List<InsumoDto> findAllDtos(List<Insumo> insumos) {
    return insumos.stream().map(InsumoDto::new).toList();
  }

  
  public Insumo save(Insumo insumo) {
    return insumoRepository.save(insumo);
  }

  public Insumo findById(Long id) {
    return insumoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não encontrado o Insumo com ID " + id));
  }

  public void delete(Long id) {
    Insumo insumo = findById(id);
    insumoRepository.delete(insumo);
  }

}

