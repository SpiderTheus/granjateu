package com.spider.granjateu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spider.granjateu.dtos.InsumoDto;
import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.InsumoRepository;
import com.spider.granjateu.services.exceptions.AttributeMandatoryException;

import com.spider.granjateu.services.exceptions.NotFoundException;
import com.spider.granjateu.services.exceptions.StatusInvalidException;

@Service
public class InsumoService  {
  private final InsumoRepository insumoRepository;

  public InsumoService(InsumoRepository insumoRepository) {
    this.insumoRepository = insumoRepository;
  }

  public Insumo findById(Long id) {
    return insumoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não encontrado o Insumo com ID " + id));
  }

  public List<Insumo> findAll() {
    return insumoRepository.findAll();
  }

  public List<Insumo> findByTipoString(String tipo) {
    try {
      AveStatus parsedTipo = AveStatus.parseStatus(tipo);

      return findByTipo(parsedTipo);

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

  public Insumo update(Long id, InsumoDto insumoDto) {
    Insumo existingInsumo = findById(id);

    Insumo updatedInsumo = manterAtributo(existingInsumo, insumoDto);

    return insumoRepository.save(updatedInsumo);
  }

 
  public Insumo manterAtributo(Insumo existingInsumo, InsumoDto insumoDto) {
    if (insumoDto.getTipo() != null) {
      AveStatus parsedTipo = AveStatus.parseStatus(insumoDto.getTipo());
      existingInsumo.setTipo(parsedTipo);
    }
    if (insumoDto.getQuantidade() != 0) {
      existingInsumo.setQuantidade(insumoDto.getQuantidade());
    }
    if (insumoDto.getValor() != null) {
      existingInsumo.setValor(insumoDto.getValor());
    }
    if(insumoDto.getDataEntrada() != null) {
      existingInsumo.setDataEntrada(insumoDto.getDataEntrada());
    }
    return existingInsumo;
  }

  public Insumo createInsumo(InsumoDto insumoDto) {
    
  try {
      Insumo insumo = new Insumo(insumoDto);
      return save(insumo);
    } catch (NullPointerException e) {
      throw new AttributeMandatoryException(e.getMessage());
    }
  }

  
  public Insumo save(Insumo insumo) {

 if (insumo.getTipo() == null || insumo.getQuantidade() == null || insumo.getValor() == null) {
      throw new AttributeMandatoryException("Os campos tipo, quantidade e valor são obrigatórios para criar um insumo.");  
    }

    return insumoRepository.save(insumo);
  }


  public void delete(Long id) {
    Insumo insumo = findById(id);
    insumoRepository.delete(insumo);
  }

}

