package com.spider.granjateu.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spider.granjateu.dtos.ManejoSemanalDto;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.entities.ManejoSemanal;
import com.spider.granjateu.repositories.ManejoSemanalRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;



@Service
public class ManejoSemanalService {

  ManejoSemanalRepository manejoSemanalRepository;

  LoteAvesService loteAvesService;

  public ManejoSemanalService(ManejoSemanalRepository manejoSemanalRepository, LoteAvesService loteAvesService) {
    this.manejoSemanalRepository = manejoSemanalRepository;
    this.loteAvesService = loteAvesService;
  }

  public ManejoSemanal findById(Long id) {
    return manejoSemanalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Manejo semanal não encontrado com id: " + id));
  }

  public List<ManejoSemanalDto> findAll() {
    return changeToDto(manejoSemanalRepository.findAll());
  }

  public List<ManejoSemanalDto> findByLoteAvesId(Long loteAvesId) {
    try {
      loteAvesService.findById(loteAvesId);
      return changeToDto(manejoSemanalRepository.findByLoteAvesId(loteAvesId));
    } catch (Exception e) {
      throw new NotFoundException("Lote de aves não encontrado com id: " + loteAvesId);
    }
  }

  public List<ManejoSemanalDto> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal) {
      if (dataInicial.isAfter(dataFinal)) {
          throw new IllegalArgumentException("Data inicial deve ser anterior à data final");
      }

      List<ManejoSemanal> manejos = manejoSemanalRepository.findByDataBetween(dataInicial, dataFinal);

      if (manejos.isEmpty()) {
          throw new NotFoundException("Nenhum manejo semanal encontrado entre as datas");
      }

      return changeToDto(manejos);
    
  }

  public List<ManejoSemanalDto> changeToDto(List<ManejoSemanal> manejos) {
   if (manejos == null || manejos.isEmpty()) {
      throw new NotFoundException("Nenhum manejo semanal encontrado para conversão");
    }

    return manejos.stream()
            .map(ManejoSemanalDto::new)
            .toList();
}  
  public ManejoSemanalDto create(ManejoSemanalDto manejoSemanalDto) {
    LoteAves loteAves = loteAvesService.findById(manejoSemanalDto.getLoteAvesId());

    if (manejoSemanalDto.getPerdas() > 0) {
      loteAves.subtrairQuantidadeBaixas(manejoSemanalDto.getPerdas());
      loteAvesService.save(loteAves);
    }

    ManejoSemanal manejoSemanal = new ManejoSemanal(loteAves, manejoSemanalDto);
    return new ManejoSemanalDto(save(manejoSemanal));
  }

  public ManejoSemanal save(ManejoSemanal manejoSemanal) {
    return manejoSemanalRepository.save(manejoSemanal);
  }

  public ManejoSemanalDto update(Long id, ManejoSemanalDto manejoSemanalDto) {
    ManejoSemanal manejoSemanal = findById(id);
    manejoSemanal = manterAtributos(manejoSemanal, manejoSemanalDto);

    return new ManejoSemanalDto(manejoSemanal);
}
  
  public ManejoSemanal manterAtributos(ManejoSemanal manejoSemanal, ManejoSemanalDto manejoSemanalDto) {
    if (manejoSemanalDto.getObservacao() != null) {
      manejoSemanal.setObservacao(manejoSemanalDto.getObservacao());
    }
    if (manejoSemanalDto.getConsumo() != null) {
      manejoSemanal.setConsumo(manejoSemanalDto.getConsumo());
    }
    if (manejoSemanalDto.getPerdas() != 0) {
      manejoSemanal.setPerdas(manejoSemanalDto.getPerdas());
    }
    if (manejoSemanalDto.getOvosColetados() != 0) {
      manejoSemanal.setOvosColetados(manejoSemanalDto.getOvosColetados());}
    if (manejoSemanalDto.getData() != null) {
      manejoSemanal.setData(manejoSemanalDto.getData());
    }
     if (manejoSemanalDto.getLoteAvesId() != 0) {
      LoteAves loteAves = loteAvesService.findById(manejoSemanalDto.getLoteAvesId());
      manejoSemanal.setLoteAves(loteAves);
     }
    return save(manejoSemanal);
  }
  public void delete(Long id) {
    ManejoSemanal manejoSemanal = findById(id);
    manejoSemanalRepository.delete(manejoSemanal);
  }


}
