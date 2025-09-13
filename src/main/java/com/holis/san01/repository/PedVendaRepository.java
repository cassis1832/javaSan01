package com.holis.san01.repository;

import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedVendaRepository extends JpaRepository<PedVenda, Integer> {

    @Query("select p from PedVenda p Where p.nrPedido = ?1")
    Optional<PedVenda> findPedVendaByNrPedido(Integer nrPedido);

    @Query("select p from PedVenda p Where p.codEntd = ?1")
    List<PedVenda> listPedVendas(int codEntd);
}
