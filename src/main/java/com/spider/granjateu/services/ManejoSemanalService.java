package com.spider.granjateu.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

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

  public List<ManejoSemanal> findAll() {
    return manejoSemanalRepository.findAll();
  }

  public List<ManejoSemanal> findByLoteAvesId(Long loteAvesId) {
    try {
      loteAvesService.findById(loteAvesId);
      return manejoSemanalRepository.findByLoteAvesId(loteAvesId);

    } catch (Exception e) {
      throw new NotFoundException("Lote de aves não encontrado com id: " + loteAvesId);
    }
  }

  public List<ManejoSemanal> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal) {
      if (dataInicial.isAfter(dataFinal)) {
          throw new IllegalArgumentException("Data inicial deve ser anterior à data final");
      }

      List<ManejoSemanal> manejos = manejoSemanalRepository.findByDataBetween(dataInicial, dataFinal);

      if (manejos.isEmpty()) {
          throw new NotFoundException("Nenhum manejo semanal encontrado entre as datas");
      }

      return manejos;
    
  }

}
