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
@Table(name = "postura_registro")
@Getter
@Setter
public class RegistroPostura implements java.io.Serializable { 
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "lote_aves_id")
  private LoteAves loteAves;

  private int ovosProduzidos;
  private LocalDate data;

  public RegistroPostura() {
  }

  public RegistroPostura(LoteAves loteAves, int ovosProduzidos) {
    this.loteAves = loteAves;
    this.ovosProduzidos = ovosProduzidos;
    this.data = LocalDate.now();
  }
  
}
