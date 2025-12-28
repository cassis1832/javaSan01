package com.holis.san01.repository;

import com.holis.san01.model.PedItem;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedItemRepository extends JpaRepository<PedItem, Integer>, JpaSpecificationExecutor<PedItem> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PedItem p WHERE LOWER(p.codItem) = LOWER(:codItem)")
    boolean existsByCodItem(@Param("codItem") String codItem);

    @Nonnull
    Optional<PedItem> findById(@Nonnull Integer id);
}

