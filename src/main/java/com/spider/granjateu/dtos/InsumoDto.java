package com.spider.granjateu.dtos;

import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.enums.AveStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoDto {
    
  private Long loteAvesId;
  private String tipo;
  private Double quantidade; 
  private Double valor;
  private String dataEntrada;

  public InsumoDto() {
  }

  public InsumoDto(Insumo insumo) {
    this.loteAvesId = insumo.getLoteAves().getId();
    insumo.getTipo();
    this.tipo = AveStatus.getEnumString(insumo.getTipo());
    this.quantidade = insumo.getQuantidade();
    this.valor = insumo.getValor();
    this.dataEntrada = insumo.getDataEntrada().toString();
    
  }

}
