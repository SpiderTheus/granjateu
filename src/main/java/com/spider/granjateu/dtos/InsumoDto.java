package com.spider.granjateu.dtos;

import com.spider.granjateu.entities.Insumo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoDto {
    
  private Long loteAvesId;
  private String tipo;
  private Double quantidade; 
  private Double valor;

  public InsumoDto() {
  }

  public InsumoDto(Insumo insumo) {
    this.loteAvesId = insumo.getLoteAves().getId();
    this.tipo = insumo.getTipo().getEnumString(insumo.getTipo());
    this.quantidade = insumo.getQuantidade();
    this.valor = insumo.getValor();
  }

}
