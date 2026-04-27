package com.spider.granjateu.entities;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spider.granjateu.dtos.ManejoSemanalDto;

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
public class ManejoSemanal implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "lote_aves_id")
  private LoteAves loteAves;
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate data;
  private String observacao;
  private double consumo;
  private int perdas;
  private int semana;
  private int ovosColetados;

  public ManejoSemanal() {
  }

  public ManejoSemanal(LoteAves loteAves, ManejoSemanalDto manejoSemanalDto) {
    this.loteAves = loteAves;
    this.data = LocalDate.now();
    this.observacao = manejoSemanalDto.getObservacao();
    this.consumo = manejoSemanalDto.getConsumo();
    this.perdas = manejoSemanalDto.getPerdas();
    this.semana = loteAves.getSemana();
    this.ovosColetados = manejoSemanalDto.getOvosColetados();
  }

}
