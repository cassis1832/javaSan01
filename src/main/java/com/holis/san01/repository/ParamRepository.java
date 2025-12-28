package com.holis.san01.repository;

import com.holis.san01.model.Param;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParamRepository extends JpaRepository<Param, String> {

    @Nonnull
    @Query("select p from Param p Where lower(p.codParam) =  lower(?1)")
    Optional<Param> findById(@Nonnull String codParam);
}