package com.holis.san01.repository;

import com.holis.san01.model.VwPedItem;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * View das linhas do pedido de venda
 */
@Immutable
public interface VwPedItemRepository extends JpaRepository<VwPedItem, Integer>, JpaSpecificationExecutor<VwPedItem> {

}