package com.holis.san01.repository;

import com.holis.san01.model.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParamRepository extends JpaRepository<Param, String> {
    @Query("select p from Param p Where upper(p.codParam) =  upper(?1)")
    Optional<Param> getParam(String codParam);
}