package com.spider.granjateu.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteAvesDto {

    private String raca;
    private int quantidade;
    private double valor;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    private int semanas;
    private String status; 
    private String objetivo;

    public LoteAvesDto() {
    }

    public LoteAvesDto(LoteAves loteAves) {
        this.raca = loteAves.getRaca();
        this.quantidade = loteAves.getQuantidade();
        this.valor = loteAves.getValor();
        this.dataDeNascimento = loteAves.getDataDeNascimento();
        
        this.semanas = loteAves.getSemana();
        loteAves.getStatus();
        this.status = AveStatus.getEnumString(loteAves.getStatus()); 
        loteAves.getObjetivo();
        this.objetivo = AveStatus.getEnumString(loteAves.getObjetivo());
    }


}
