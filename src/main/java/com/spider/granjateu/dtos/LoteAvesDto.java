package com.spider.granjateu.dtos;

import com.spider.granjateu.entities.LoteAves;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteAvesDto {

    private String raca;
    private int quantidade;
    private double pesoMedio;
    private int semanas;
    private String status; 


    public LoteAvesDto() {
    }

    public LoteAvesDto(LoteAves loteAves) {
        this.raca = loteAves.getRaca();
        this.quantidade = loteAves.getQuantidade();
        this.semanas = loteAves.getSemana();
        this.status = loteAves.getStatus().toString();
    }


}
