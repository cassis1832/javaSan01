package com.holis.san01.repository;

import com.holis.san01.model.PedItem;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedItemRepository extends JpaRepository<PedItem, Integer> {

    boolean existsByCodItem(String codItem);

    boolean existsById(@Nonnull Integer id);

    Optional<PedItem> findPedItemById(Integer id);
}

