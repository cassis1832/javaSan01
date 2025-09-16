package com.holis.san01.repository;

import com.holis.san01.model.PedVendaItem;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedVendaItemRepository extends JpaRepository<PedVendaItem, Integer> {

    boolean existsByCodItem(String codItem);

    boolean existsById(@Nonnull Integer id);

    /**
     * Ler ped_venda_item pelo ID
     */
    @Query("select pi from PedVendaItem pi Where pi.id = ?1")
    Optional<PedVendaItem> findPedVendaItemById(Integer id);
}

