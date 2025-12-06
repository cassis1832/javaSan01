package com.holis.san01.repository;

import com.holis.san01.model.CondPag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondPagRepository extends JpaRepository<CondPag, String>, JpaSpecificationExecutor<CondPag> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM CondPag i WHERE LOWER(i.codCondPag) = LOWER(:codCondPag)")
    boolean existsByCodCondPag(@Param("codCondPag") String codCondPag);

    @Query("SELECT e FROM CondPag e WHERE LOWER(codCondPag) = LOWER(?1)")
    Optional<CondPag> findByCodCondPag(String codCondPag);

    @Query("DELETE FROM CondPag i WHERE LOWER(i.codCondPag) = LOWER(:codCondPag)")
    void deleteByCodCondPag(@Param("codCondPag") String codCondPag);
}
