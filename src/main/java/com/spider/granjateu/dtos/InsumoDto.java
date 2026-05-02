package com.spider.granjateu.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoDto {

    private String tipo;
    private Double quantidade;
    private Double valor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEntrada;

    public InsumoDto() {
    }

    public InsumoDto(String tipo, Double quantidade, Double valor, LocalDate dataEntrada) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
    }

}
