package com.holis.san01.repository;

import com.holis.san01.model.PedVendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedVendaItemRepository extends JpaRepository<PedVendaItem, Integer> {

    /**
     * Ler ped_venda_item pelo ID
     */
    @Query("select pi from PedVendaItem pi Where " +
            "pi.id = ?1")
    Optional<PedVendaItem> findPedVendaItem(Long id);

    /**
     * Ler ped_venda_item pelo nr_pedido
     */
    @Query("select pi from PedVendaItem pi Where " +
            "pi.nrPedido = ?1 " +
            "order by nrSequencia, codItem")
    List<PedVendaItem> listPedVendaItem(Long nrPedido);
}
