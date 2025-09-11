package com.holis.san01.repository;

import com.holis.san01.model.VwPedVenda;
import org.hibernate.annotations.Immutable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Immutable
public interface VwPedVendaRepository extends JpaRepository<VwPedVenda, Integer> {
    @Query("select vw from VwPedVenda vw where vw.status = ?1 ")
    List<VwPedVenda> ListVwPedVenda(Integer status);

    @Query("select vw from VwPedVenda vw where vw.status = ?1 ")
    Page<VwPedVenda> PageVwPedVenda(Integer status, Pageable pageable);

    @Query("select v from VwPedVenda v Where v.status = ?1")
    Page<VwPedVenda> pageVwPedVenda(Integer status, Pageable pageable);

    @Query("select v from VwPedVenda v Where v.status = ?1 and " +
            "(v.nome = ?2 ) ")
    Page<VwPedVenda> pageVwPedVendaByFilter(Integer status, String filterText, Pageable pageable);
}

