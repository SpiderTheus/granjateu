package com.spider.granjateu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.enums.AveStatus;


public interface LoteAvesRepository extends JpaRepository<LoteAves, Long>{
    
    @Query("SELECT l FROM LoteAves l WHERE l.status = :status")
    List<LoteAves> findByStatus(@Param("status") AveStatus status);

    @Query("SELECT l FROM LoteAves l WHERE l.raca = :raca")
    List<LoteAves> findByRaca(@Param("raca") String raca);


}