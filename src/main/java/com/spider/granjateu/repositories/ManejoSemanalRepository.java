package com.spider.granjateu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spider.granjateu.entities.ManejoSemanal;

public interface ManejoSemanalRepository extends JpaRepository<ManejoSemanal, Long> {

    @Query("SELECT m FROM ManejoSemanal m WHERE m.loteAves.id = :loteAvesId")
    List<ManejoSemanal> findByLoteAvesId(@Param("loteAvesId") Long loteAvesId);

    List<ManejoSemanal> findByDataBetween(@Param("dataInicial") java.time.LocalDate dataInicial, @Param("dataFinal") java.time.LocalDate dataFinal);



}
