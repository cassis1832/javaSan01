package com.holis.san01.repository;

import com.holis.san01.model.Situacao;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Integer> {

    boolean existsById(@Nonnull Integer id);

    boolean existsByObjetoAndDescricao(@Nonnull String objeto, String descricao);

    Optional<Situacao> findSituacaoById(Integer id);

    @Query("select s from Situacao s Where s.objeto =  ?1 order by s.sequencia, s.descricao")
    List<Situacao> listSituacao(String objeto);
}
