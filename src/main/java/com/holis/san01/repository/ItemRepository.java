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
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM Item i WHERE i.empresa = :empresa AND i.id = :id")
    boolean existsById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM Item i WHERE i.empresa = :empresa AND LOWER(i.codItem) = LOWER(:codItem)")
    boolean existsByCodItem(@Param("empresa") Integer empresa, @Param("codItem") String codItem);

    @Query("SELECT i FROM Item i WHERE i.empresa = :empresa AND i.id = :id)")
    Optional<Item> findById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT i FROM Item i WHERE i.empresa = :empresa AND LOWER(i.codItem) = LOWER(:id)")
    Optional<Item> findByCodItem(@Param("empresa") Integer empresa, @Param("codItem") String codItem);

    @Query("DELETE FROM Item i WHERE i.empresa = :empresa AND i.id = :id")
    void deleteById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT DISTINCT i.codFamilia FROM Item i WHERE i.empresa = :empresa AND i.codFamilia <> '' ORDER BY i.codFamilia")
    List<String> listFamilias(@Param("empresa") Integer empresa);

    @Query("SELECT DISTINCT i.situacao FROM Item i WHERE i.empresa = :empresa AND i.situacao <> '' ORDER BY i.situacao")
    List<String> listSituacoes(@Param("empresa") Integer empresa);
}
