package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {
    @Query("select e from Entidade e Where e.codEntd = ?1")
    Optional<Entidade> findEntidade(Integer codEntd);

    @Query("select e from Entidade e Where e.archive = ?1 ")
    Page<Entidade> listEntidades(String archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "(concat(e.codEntd,e.nome,e.razaoSocial,e.cgc) like concat('%',?2,'%'))")
    Page<Entidade> listEntidades(String archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indCliente = 'S'")
    Page<Entidade> listClientes(String archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indCliente = 'S' and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%'))")
    Page<Entidade> listClientes(String archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec = 'S'")
    Page<Entidade> listFornecedores(String archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec = 'S' and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%')) ")
    Page<Entidade> listFornecedores(String archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec  = 'N' and e.indCliente = 'N'")
    Page<Entidade> listNenhumTipo(String archive, Pageable pageable);

    @Query("select e from Entidade e Where e.archive = ?1 and " +
            "e.indFornec  = 'N' and e.indCliente = 'N' and " +
            "(e.razaoSocial like concat('%',?2,'%') or e.nome like concat('%',?2,'%'))")
    Page<Entidade> listNenhumTipo(String archive, String filterText, Pageable pageable);
}
