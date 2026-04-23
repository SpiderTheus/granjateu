package com.spider.granjateu.services;

import java.util.List;

import org.springframework.stereotype.Service;

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


  public Insumo update(Long id, Insumo insumo) {
    Insumo existingInsumo = findById(id);
    
    return insumoRepository.save(manterAtributo(existingInsumo, insumo));
  }


  public Insumo manterAtributo(Insumo existingInsumo, Insumo insumo) {
    if (insumo.getTipo() != null) {
      existingInsumo.setTipo(insumo.getTipo());
    }
    if (insumo.getQuantidade() != null) {
      existingInsumo.setQuantidade(insumo.getQuantidade());
    }
    if (insumo.getValor() != null) {
      existingInsumo.setValor(insumo.getValor());
    }
    if(insumo.getDataEntrada() != null) {
      existingInsumo.setDataEntrada(insumo.getDataEntrada());
    }
    return existingInsumo;
  }

  public Insumo createInsumo(Insumo insumo) {
  try {
      return save(insumo);
    } catch (AttributeMandatoryException e) {
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

