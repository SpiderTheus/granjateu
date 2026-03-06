package com.spider.granjateu.entities;

import java.time.LocalDate;
import java.util.List;

import com.spider.granjateu.enums.AveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lote_aves")
public class LoteAves implements java.io.Serializable {
  private static final long serialVersionUID = 1L;  

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 
  
  private String raca;
  private int quantidade;
  private double valor;
  private LocalDate dataDeNascimento;
  private LocalDate dataDeChegada;
  private double pesoMedio;
  private AveStatus status;

  @OneToMany(mappedBy = "loteAves")
  private List<Insumo> insumos;

  public LoteAves() {
  }

  public LoteAves(String raca, int quantidade, double valor, LocalDate dataDeNascimento, LocalDate dataDeChegada, double pesoMedio, AveStatus status) {
    this.raca = raca;
    this.quantidade = quantidade;
    this.valor = valor;
    this.dataDeNascimento = dataDeNascimento;
    this.dataDeChegada = dataDeChegada;
    this.pesoMedio = pesoMedio;
    this.status = status;
  }

}
