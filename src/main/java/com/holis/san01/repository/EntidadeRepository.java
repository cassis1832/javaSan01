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
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    @Query("select e from Entidade e Where " +
            "e.codEntd = ?1")
    Optional<Entidade> findEntidade(Long codEntd);

    @Query("select e from Entidade e Where " +
            "e.archive = ?1 " +
            "order by codEntd")
    Page<Entidade> listEntidades(String archive, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.archive = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> listEntidades(String archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indCliente = 'S' and " +
            "e.archive = ?1 " +
            "order by codEntd")
    Page<Entidade> listClientes(String archive, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indCliente = 'S' and " +
            "e.archive = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> listClientes(String archive, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indFornec = 'S' and " +
            "e.archive = ?1 " +
            "order by codEntd")
    Page<Entidade> listFornecedores(String archive, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indFornec = 'S' and " +
            "e.archive = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> listFornecedores(String archive, String filterText, Pageable pageable);

}
