package com.holis.san01.repository;

import com.holis.san01.model.VwPedVendaItem;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * View das linhas do pedido de venda
 */
@Immutable
public interface VwPedVendaItemRepository extends JpaRepository<VwPedVendaItem, Integer> {

    /**
     * Listar os itens de um determinado pedido
     */
    @Query("select v from VwPedVendaItem v where nrPedido = ?1 " +
            "order by nrSequencia, codItem")
    List<VwPedVendaItem> listVwPedVendaItemByPedido(Integer nrPedido);

    /**
     * Listar os itens dos pedidos por status
     */
    @Query("select v from VwPedVendaItem v Where v.status = ?1 " +
            "order by nrPedido, nrSequencia, codItem")
    Page<VwPedVendaItem> pageVwPedVendaItem(Integer status, Pageable pageable);

    /**
     * Listar os itens dos pedidos por status e filtro
     */
    @Query("select v from VwPedVendaItem v Where v.status = ?1 and " +
            "(v.nome = ?2 or v.descricao = ?2) " +
            "order by codEntd, nrSequencia, codItem")
    Page<VwPedVendaItem> pageVwPedVendaItemByFilter(Integer status, String filterText, Pageable pageable);
}