package com.holis.san01.repository;

import com.holis.san01.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("select i from Item i Where upper(i.codItem) =  upper(?1)")
    Optional<Item> findItem(String codItem);
}
