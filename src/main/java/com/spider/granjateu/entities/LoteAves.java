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
  
  private AveStatus status;

  @OneToMany(mappedBy = "loteAves")
  private List<Insumo> insumos;

  @OneToMany(mappedBy = "loteAves")
  private List<RegistroManejo> registrosManejo;

  @OneToMany(mappedBy = "loteAves")
  private List<RegistroPostura> registrosPostura;

  public LoteAves() {
  }

  public LoteAves(String raca, int quantidade, double valor, LocalDate dataDeNascimento, AveStatus status) {
    this.raca = raca;
    this.quantidade = quantidade;
    this.valor = valor;
    this.dataDeNascimento = dataDeNascimento;  
    this.status = status;
  }

  public void subtrairQuantidadeBaixas(int baixas) {
    if (baixas < 0) {
        this.quantidade = 0;
    }

    if (baixas > this.quantidade) {
        throw new IllegalArgumentException("A quantidade de baixas não pode exceder a quantidade atual de aves.");
    }
    
    this.quantidade -= baixas;
  }

}
