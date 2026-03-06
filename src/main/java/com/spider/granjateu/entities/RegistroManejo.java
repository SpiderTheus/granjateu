package com.spider.granjateu.entities;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "registro_manejo")
@Getter
@Setter
public class RegistroManejo implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "lote_aves_id")
  private LoteAves loteAves;
  
  private LocalDate data;
  private String observacao;
  private double consumo;
  private double pesoMedio;
  private int perdas;
  

  public RegistroManejo() {
  }

  public RegistroManejo(LoteAves loteAves, String observacao, double consumo, double pesoMedio, int perdas) {
    this.loteAves = loteAves;
    this.data = LocalDate.now();
    this.observacao = observacao;
    this.consumo = consumo;
    this.pesoMedio = pesoMedio;
    this.perdas = perdas;
  }

}
