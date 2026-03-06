package com.spider.granjateu.entities;

import com.spider.granjateu.enums.AveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "insumo")
@Getter
@Setter
public class Insumo implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "lote_aves_id")
  private LoteAves loteAves;

  private AveStatus tipo;
  private Double quantidade;
  private Double valor;

  public Insumo() {
  } 

  public Insumo(LoteAves loteAves, AveStatus tipo, Double quantidade, Double valor) {
    this.loteAves = loteAves;
    this.tipo = tipo;
    this.quantidade = quantidade;
    this.valor = valor;
  }
}
