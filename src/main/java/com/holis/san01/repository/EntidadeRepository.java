package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>, JpaSpecificationExecutor<Entidade> {

    boolean existsByCodEntd(Integer codEntd);

    @Query("select e from Entidade e Where e.codEntd = ?1")
    Optional<Entidade> findEntidadeByCodEntd(Integer codEntd);
}
