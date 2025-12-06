package com.holis.san01.repository;

import com.holis.san01.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParamRepository extends JpaRepository<Parametro, Integer> {

    @Query("SELECT p from Parametro p WHERE p.empresa = :empresa AND LOWER(p.codParam) =  LOWER(:codParam)")
    Optional<Parametro> findByCodParam(@Param("empresa") Integer empresa, @Param("codParam") String codParam);
}