package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {
    @Query("select e from Entidade e Where e.codEntd = ?1")
    Optional<Entidade> getEntidade(Integer codEntd);

    @Query("select e from Entidade e Where e.archive = ?1 ")
    Page<Entidade> pageEntidades(Boolean archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "(concat(e.codEntd,e.nome,e.razaoSocial,e.cgc) like concat('%',?2,'%'))")
    Page<Entidade> pageEntidades(Boolean archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and e.indCliente = true")
    Page<Entidade> pageClientes(Boolean archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and e.indCliente = true and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%'))")
    Page<Entidade> pageClientes(Boolean archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and e.indFornec = true")
    Page<Entidade> pageFornecedores(Boolean archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and e.indFornec = true and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%')) ")
    Page<Entidade> pageFornecedores(Boolean archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec  = false and e.indCliente = false")
    Page<Entidade> pageNenhumTipo(Boolean archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec  = false and e.indCliente = false and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%'))")
    Page<Entidade> pageNenhumTipo(Boolean archive, String filterText, Pageable pageable);
}
