package com.holis.san01.repository;

import com.holis.san01.model.CondPag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondPagRepository extends JpaRepository<CondPag, Integer>, JpaSpecificationExecutor<CondPag> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM CondPag i WHERE i.empresa = :empresa AND i.id = :id")
    boolean existsById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM CondPag i WHERE i.empresa = :empresa AND LOWER(i.codCondPag) = LOWER(:codCondPag)")
    boolean existsByCodCondPag(@Param("empresa") Integer empresa, @Param("codCondPag") String codCondPag);

    @Query("SELECT e FROM CondPag e WHERE e.empresa = :empresa AND e.id = :id")
    Optional<CondPag> findById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT e FROM CondPag e WHERE e.empresa = :empresa AND LOWER(e.codCondPag) = LOWER(:codCondPag)")
    Optional<CondPag> findByCodCondPag(@Param("empresa") Integer empresa, @Param("codCondPag") String codCondPag);

    @Query("DELETE FROM CondPag i WHERE i.empresa = :empresa AND i.id = :id")
    void deleteById(@Param("empresa") Integer empresa, @Param("id") Integer id);
}
