package com.spider.granjateu.entities;

import java.time.Instant;

import com.spider.granjateu.enums.AveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private AveStatus tipo;
  private Double quantidade;
  private Double valor;
  
  private Instant dataEntrada;

  public Insumo() {
  } 

  public Insumo( AveStatus tipo, Double quantidade, Double valor) {

    this.tipo = tipo;
    this.quantidade = quantidade;
    this.valor = valor;
    this.dataEntrada = Instant.now();
  }
}
