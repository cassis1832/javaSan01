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

    Optional<Item> findByCodItemIgnoreCase(String codItem);

    boolean existsByCodItemIgnoreCase(String codItem);

    void deleteByCodItemIgnoreCase(String codItem);

    @Query("UPDATE Item SET status = :status WHERE LOWER(codItem) = LOWER(:codItem)")
    void archive(@Param("codItem") String codItem, @Param("status") Integer status);

    @Query("SELECT DISTINCT i.codFamilia FROM Item i WHERE i.codFamilia <> '' ORDER BY i.codFamilia")
    List<String> listFamilias();

    @Query("SELECT DISTINCT i.situacao FROM Item i WHERE i.situacao <> '' ORDER BY i.situacao")
    List<String> listSituacoes();
}
