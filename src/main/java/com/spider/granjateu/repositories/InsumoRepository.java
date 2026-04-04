package com.spider.granjateu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spider.granjateu.entities.Insumo;
import com.spider.granjateu.enums.AveStatus;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    @Query("SELECT i FROM Insumo i WHERE i.loteAves.id = :loteAvesId")
    List<Insumo> findByLoteAvesId(Long loteAvesId);

    @Query("SELECT i FROM Insumo i WHERE i.fase = :fase")
    List<Insumo> findByFase(AveStatus fase);

}
