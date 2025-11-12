package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>, JpaSpecificationExecutor<Entidade> {

    boolean existsByCodEntd(Integer codEntd);

    @Query("SELECT e FROM Entidade e WHERE e.codEntd = :codEntd")
    Optional<Entidade> findByCodEntd(@Param("codEntd") Integer codEntd);
}
