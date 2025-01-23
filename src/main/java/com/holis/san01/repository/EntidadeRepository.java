package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {
    @Query("select e from Entidade e Where " +
            "e.codEntd = ?1")
    Optional<Entidade> findEntidade(Integer codEntd);

    @Query("select e from Entidade e Where " +
            "e.status = ?1 " +
            "order by codEntd")
    Page<Entidade> findEntidades(String status, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.status = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> findEntidades(String status, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indCliente = 'S' and " +
            "e.status = ?1 " +
            "order by codEntd")
    Page<Entidade> findClientes(String status, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indCliente = 'S' and " +
            "e.status = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> findClientes(String status, String filterText, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indFornec = 'S' and " +
            "e.status = ?1 " +
            "order by codEntd")
    Page<Entidade> findFornecedores(String status, Pageable pageable);

    @Query("select e from Entidade e Where " +
            "e.indFornec = 'S' and " +
            "e.status = ?1 and " +
            "(e.razaoSocial like concat('%',?1,'%') or e.nome like concat('%',?1,'%')) " +
            "order by codEntd")
    Page<Entidade> findFornecedores(String status, String filterText, Pageable pageable);

}
