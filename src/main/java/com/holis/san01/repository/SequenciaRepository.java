package com.holis.san01.repository;

import com.holis.san01.model.Sequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SequenciaRepository extends JpaRepository<Sequencia, String> {
    @Query("select s from Sequencia s where s.codSeq = ?1")
    Optional<Sequencia> findSequencia(String codSequencia);

    @Query("select s from Sequencia s order by codSeq")
    List<Sequencia> findSequencias();
}