package com.spider.granjateu.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.spider.granjateu.dtos.LoteAvesDto;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;
import com.spider.granjateu.repositories.LoteAvesRepository;
import com.spider.granjateu.services.exceptions.NotFoundException;
import com.spider.granjateu.services.exceptions.StatusInvalidException;

@Service
public class LoteAvesService {

    private final LoteAvesRepository loteAvesRepository;

    public LoteAvesService(LoteAvesRepository loteAvesRepository) {
        this.loteAvesRepository = loteAvesRepository;
    }

    public List<LoteAvesDto> findByStatus(String status) {
        try {  
            AveStatus parsedStatus = parseStatus(status);
            List<LoteAves> lotes = verificarLista(loteAvesRepository.findByStatus(parsedStatus));
         
            return findAllDtos(lotes); 

        } catch (StatusInvalidException | NotFoundException e) {
            return List.of();
        } 
    }
    
    public AveStatus parseStatus(String status) {
        try {
            
            return AveStatus.valueOf(status.toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new StatusInvalidException(status);
        }
    }

    public List<LoteAves> verificarLista(List<LoteAves> lotes) {
    if (lotes.isEmpty()) {
        throw new NotFoundException("Nenhum lote de aves encontrado com o status especificado");
       }
       return lotes;
    }

        public List<LoteAvesDto> findByRaca(String raca) {
        try {
            return findAllDtos(buscarPorRaca(raca));
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

    public List<LoteAvesDto> findAllDtos(List<LoteAves> lotes) {
        return lotes.stream().map(LoteAvesDto::new).toList();
    }
        
    public List<LoteAvesDto> findAll() {
        return findAllDtos(loteAvesRepository.findAll());
    }

    public LoteAvesDto findByIdDto(Long id) {
        LoteAves loteAves = findById(id);
        return new LoteAvesDto(loteAves);
    }

    public LoteAves findById(Long id) {
        return loteAvesRepository.findById(id).orElseThrow(() -> new NotFoundException("Lote de Aves com ID " + id));
    }

    public LoteAvesDto update(Long id, LoteAvesDto loteAvesDto) {
        LoteAves existingLote = findById(id);

        existingLote = manterAtributo(existingLote, loteAvesDto);
        
        return new LoteAvesDto(loteAvesRepository.save(existingLote));
    }

    public LoteAves manterAtributo(LoteAves existingLote, LoteAvesDto loteAvesDto) {
        if (loteAvesDto.getRaca() != null) {
            existingLote.setRaca(loteAvesDto.getRaca());
        }
        if (loteAvesDto.getQuantidade() != 0) {
            existingLote.setQuantidade(loteAvesDto.getQuantidade());
        }
        if (loteAvesDto.getStatus() != null && !loteAvesDto.getStatus().isEmpty()) {

            existingLote.setStatus(parseStatus(loteAvesDto.getStatus()));
        } 
        if (loteAvesDto.getValor() != 0) {
            existingLote.setValor(loteAvesDto.getValor());
        }
        if (loteAvesDto.getDataDeNascimento() != null) {
            existingLote.setDataDeNascimento(loteAvesDto.getDataDeNascimento());
        }
        return existingLote;
    }

    public LoteAvesDto create(LoteAvesDto loteAvesDto) {
        LoteAves loteAves = new LoteAves();
        loteAves.setRaca(loteAvesDto.getRaca());
        loteAves.setQuantidade(loteAvesDto.getQuantidade());
        loteAves.setStatus(parseStatus(loteAvesDto.getStatus()));
        
        return new LoteAvesDto(save(loteAves));
    }

   
    public void delete(Long id) {
        LoteAves existingLote = findById(id);
        loteAvesRepository.delete(existingLote);
    }


    public LoteAvesDto updateStatus(Long id, String status) {
        LoteAves existingLote = findById(id);
        AveStatus parsedStatus = parseStatus(status);
        existingLote.setStatus(parsedStatus);

        LoteAves atualizado = loteAvesRepository.save(existingLote);

        return new LoteAvesDto(atualizado);
    }

    public LoteAves updateQuantidadeAvesMortas(Long id, int baixas) {
        LoteAves existingLote = findById(id);
        var novaQuantidade = existingLote.getQuantidade() - baixas;
        existingLote.setQuantidade(novaQuantidade);
        
        return loteAvesRepository.save(existingLote);
    }

     public LoteAves save(LoteAves loteAves) {
        return loteAvesRepository.save(loteAves);
    }


}
