package com.holis.san01.repository;

import com.holis.san01.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("select i from Item i Where " +
            "upper(i.codItem) =  upper(?1)")
    Optional<Item> findItem(String codItem);

    @Query("select i from Item i Where " +
            "i.archive  = ?1 " +
            "order by i.codItem")
    Page<Item> listItens(String archive, Pageable pageable);

    @Query("select i from Item i where " +
            "i.archive = ?1 and " +
            "(i.codItem like concat('%',?2,'%') or i.descricao like concat('%',?2,'%')) ")
    Page<Item> listItens(String archive, String filterText, Pageable pageable);

    @Query("select i from Item i where " +
            "i.tipoItem = ?1 and " +
            "i.archive = ?2 ")
    Page<Item> listItensPorTipo(String tipoItem, String archive, Pageable pageable);

    @Query("select i from Item i where " +
            "i.tipoItem = ?1 and " +
            "i.archive = ?2 and " +
            "(i.codItem like concat('%',?3,'%') or i.descricao like concat('%',?3,'%')) ")
    Page<Item> listItensPorTipo(String tipoItem, String archive, String filterText, Pageable pageable);
}
