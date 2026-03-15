package com.spider.granjateu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.repositories.LoteAvesRepository;

@Service
public class LoteAvesService {

  
    private final LoteAvesRepository loteAvesRepository;

    public LoteAvesService(LoteAvesRepository loteAvesRepository) {
        this.loteAvesRepository = loteAvesRepository;
    }

    public List<LoteAves> findAll() {
        return loteAvesRepository.findAll();
    }

}
