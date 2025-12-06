package com.holis.san01.repository;

import com.holis.san01.model.TituloAp;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TituloApRepository extends JpaRepository<TituloAp, Integer>, JpaSpecificationExecutor<TituloAp> {

    @Query("SELECT t FROM TituloAp t WHERE t.empresa = :empresa AND t.id = :id AND t.status <> 9")
    Optional<TituloAp> findById(@Nonnull @Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM TituloAp r WHERE r.empresa = :empresa AND r.codEntd = :codEntd")
    boolean existsByCodEntd(@Param("empresa") Integer empresa, @Param("codEntd") Integer codEntd);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM TituloAp r WHERE r.empresa = :empresa AND r.numDoc = :numDoc")
    boolean existsByNumDoc(@Param("empresa") Integer empresa, @Param("numDoc") Integer numDoc);

    @Query("DELETE FROM TituloAp i WHERE i.empresa = :empresa AND i.id = :id")
    void deleteById(@Param("empresa") Integer empresa, @Param("id") Integer id);
}