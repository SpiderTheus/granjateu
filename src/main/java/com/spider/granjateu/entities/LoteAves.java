package com.spider.granjateu.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spider.granjateu.enums.AveStatus;

import com.spider.granjateu.dtos.LoteAvesDto;

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
  

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataDeNascimento;
  private int semana;
  private AveStatus status;
  private AveStatus objetivo;

  @OneToMany(mappedBy = "loteAves")
  private List<ManejoSemanal> registrosManejo;

  public LoteAves() {
  }

  public LoteAves(String raca, int quantidade, double valor, LocalDate dataDeNascimento, AveStatus status, AveStatus objetivo) {
    this.raca = raca;
    this.quantidade = quantidade;
    this.valor = valor;
    this.dataDeNascimento = dataDeNascimento;
    this.semana = calcularIdadeEmSemanas();
    this.status = status;
    this.objetivo = objetivo;
  }

  public LoteAves(LoteAvesDto loteAvesDto) {
    this.raca = loteAvesDto.getRaca();
    this.quantidade = loteAvesDto.getQuantidade();
    this.valor = loteAvesDto.getValor();
    this.dataDeNascimento = loteAvesDto.getDataDeNascimento();
    this.semana = calcularIdadeEmSemanas();
    this.status = AveStatus.parseStatus(loteAvesDto.getStatus());
    this.objetivo = AveStatus.parseStatus(loteAvesDto.getObjetivo());
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

  public int calcularIdadeEmSemanas() {
    if (this.dataDeNascimento == null) {
       throw new IllegalStateException("A data de nascimento não pode ser nula para calcular a idade em semanas."); 
    }

    LocalDate hoje = LocalDate.now();
    long dias = java.time.temporal.ChronoUnit.DAYS.between(this.dataDeNascimento, hoje);
    return (int) (dias / 7);
  }


}
