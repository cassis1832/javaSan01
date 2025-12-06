package com.holis.san01.repository;

import com.holis.san01.model.PedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedItemRepository extends JpaRepository<PedItem, Integer> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PedItem p WHERE p.empresa = :empresa AND LOWER(p.codItem) = LOWER(:codItem)")
    boolean existsByCodItem(@Param("empresa") Integer empresa, @Param("codItem") String codItem);

    boolean existsById(Integer empresa, Integer id);
}

