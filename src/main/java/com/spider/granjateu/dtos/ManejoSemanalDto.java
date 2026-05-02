package com.spider.granjateu.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spider.granjateu.entities.ManejoSemanal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManejoSemanalDto {

  private long loteAvesId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate data;
  private String observacao;
  private Double consumo;
  private int perdas;
  private int ovosColetados;

  public ManejoSemanalDto() {
  }

  public ManejoSemanalDto(long loteAvesId, LocalDate data, String observacao, Double consumo, int perdas, int ovosColetados) {
    this.loteAvesId = loteAvesId;
    this.data = data;
    this.observacao = observacao;
    this.consumo = consumo;
    this.perdas = perdas;
    this.ovosColetados = ovosColetados;
  }


  public ManejoSemanalDto(ManejoSemanal manejoSemanal) {
    this.loteAvesId = manejoSemanal.getLoteAves().getId();
    this.data = manejoSemanal.getData();
    this.observacao = manejoSemanal.getObservacao();
    this.consumo = manejoSemanal.getConsumo();
    this.perdas = manejoSemanal.getPerdas();
    this.ovosColetados = manejoSemanal.getOvosColetados();
  }

  
  


}
