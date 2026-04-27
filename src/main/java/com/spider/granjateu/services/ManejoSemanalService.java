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
    ManejoSemanal manejoSemanal = new ManejoSemanal(loteAves, manejoSemanalDto);
    return new ManejoSemanalDto(save(manejoSemanal));
  }


  public ManejoSemanal save(ManejoSemanal manejoSemanal) {
    return manejoSemanalRepository.save(manejoSemanal);
  }

}
