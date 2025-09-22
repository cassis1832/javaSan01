package com.holis.san01.repository;

import com.holis.san01.model.TituloAp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TituloApRepository extends JpaRepository<TituloAp, Integer> {

    boolean existsByCodEntd(Integer codEntd);

    boolean existsByNumDoc(Integer numnDoc);

    @Query("select t from TituloAp t where t.id = ?1 and t.status <> 9")
    Optional<TituloAp> findTituloApById(Integer id);

}