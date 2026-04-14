package com.spider.granjateu.dtos;

import java.time.Instant;
import java.util.Optional;

import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.enums.AveStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoDto {
    
  private String tipo;
  private Double quantidade; 
  private Double valor;
  private String dataEntrada;

  public InsumoDto() {
  }

  public InsumoDto(Insumo insumo) {
    insumo.getTipo();
    this.tipo = AveStatus.getEnumString(insumo.getTipo());
    this.quantidade = insumo.getQuantidade();
    this.valor = insumo.getValor();
    this.dataEntrada = Optional.ofNullable(insumo.getDataEntrada())
        .map(Instant::toString)
        .orElse(null);
    
  }

}
