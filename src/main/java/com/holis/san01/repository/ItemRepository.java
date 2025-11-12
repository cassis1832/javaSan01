package com.holis.san01.repository;

import com.holis.san01.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>, JpaSpecificationExecutor<Item> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM Item i WHERE LOWER(i.codItem) = LOWER(:codItem)")
    boolean existsByCodItem(@Param("codItem") String codItem);

    @Query("SELECT i FROM Item i WHERE LOWER(i.codItem) = LOWER(:codItem)")
    Optional<Item> findByCodItem(@Param("codItem") String codItem);

    @Query("SELECT DISTINCT i.codFamilia FROM Item i WHERE i.codFamilia <> '' ORDER BY i.codFamilia")
    List<String> listFamilias();

    @Query("SELECT DISTINCT i.situacao FROM Item i WHERE i.situacao <> '' ORDER BY i.situacao")
    List<String> listSituacoes();
}
