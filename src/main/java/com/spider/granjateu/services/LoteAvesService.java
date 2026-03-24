package com.spider.granjateu.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.LoteAvesRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;
import com.spider.granjateu.services.exceptions.StatusInvalidExecption;

@Service
public class LoteAvesService {

  
    private final LoteAvesRepository loteAvesRepository;

    public LoteAvesService(LoteAvesRepository loteAvesRepository) {
        this.loteAvesRepository = loteAvesRepository;
    }

    public List<LoteAves> findAll() {
        return loteAvesRepository.findAll();
    }

    public LoteAves findById(Long id) {
        return loteAvesRepository.findById(id).orElseThrow(() -> new NotFoundException("Lote de Aves com ID " + id));
    }

    public List<LoteAves> findByStatus(String status) {
        try {  
            AveStatus parsedStatus = parseStatus(status);
            List<LoteAves> lotes = loteAvesRepository.findByStatus(parsedStatus);
            
            return verificarLista(lotes); 

        } catch (StatusInvalidExecption | NotFoundException e) {
            return List.of();
        } 

    }

    public List<LoteAves> verificarLista(List<LoteAves> lotes) {
        if (lotes.isEmpty()) {
            throw new NotFoundException("Nenhum lote de aves encontrado com o status especificado");
        }
        return lotes;
    }

    public AveStatus parseStatus(String status) {
        try {
            return AveStatus.valueOf(status.toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new StatusInvalidExecption(status);
        }
    }

    public List<LoteAves> findByRaca(String raca) {
        try {
            return buscarPorRaca(raca);
        } catch (NotFoundException e) {
            return List.of();
        }
    }

    public List<LoteAves> buscarPorRaca(String raca) {
        List<LoteAves> lotes = loteAvesRepository.findByRaca(raca);
        
        if (lotes.isEmpty()) {
            throw new NotFoundException("Nenhum lote de aves encontrado com a raça especificada");
        }
        return lotes;
    }

    public LoteAves update(Long id, LoteAves loteAves) {
        LoteAves existingLote = findById(id);

        existingLote = manterAtributo(existingLote, loteAves);
        
        return loteAvesRepository.save(existingLote);
    }

    public LoteAves manterAtributo(LoteAves existingLote, LoteAves loteAves) {
        if (loteAves.getRaca() != null) {
            existingLote.setRaca(loteAves.getRaca());
        }
        if (loteAves.getQuantidade() != 0) {
            existingLote.setQuantidade(loteAves.getQuantidade());
        }
        if (loteAves.getStatus() != null) {
            existingLote.setStatus(loteAves.getStatus());
        }
        return existingLote;
    }

    public LoteAves save(LoteAves loteAves) {
        return loteAvesRepository.save(loteAves);
    }
}
