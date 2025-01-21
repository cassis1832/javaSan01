package com.holis.san01.repository;

import com.holis.san01.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("select i from Item i Where " +
            "upper(i.codItem) =  upper(?1)")
    Optional<Item> findItem(String codItem);

    @Query("select i from Item i Where " +
            "i.status  = ?1 " +
            "order by i.codItem")
    Page<Item> findItens(String status, Pageable pageable);

    @Query("select i from Item i where " +
            "i.status = ?1 and " +
            "(i.codItem like concat('%',?2,'%') or i.descricao like concat('%',?2,'%')) ")
    Page<Item> findItens(String status, String filterText, Pageable pageable);
}
