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
public interface PedVendaRepository extends JpaRepository<PedVenda, Long> {
    /**
     * Ler um registro de pedido de venda por nr_pedido
     */
    @Query("select p from PedVenda p Where p.nrPedido = ?1")
    Optional<PedVenda> findPedVenda(Long nrPedido);

    /**
     * Listar os pedidos de um determinado cliente
     */
    @Query("select p from PedVenda p Where p.codEntd = ?1")
    List<PedVenda> listPedVendas(int codEntd);
}
