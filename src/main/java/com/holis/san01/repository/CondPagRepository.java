package com.holis.san01.repository;

import com.holis.san01.model.CondPag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondPagRepository extends JpaRepository<CondPag, String>, JpaSpecificationExecutor<CondPag> {

    boolean existsByCodCondPag(String codCondPag);
    
    @Query("select e from CondPag e Where upper(codCondPag) =  upper(?1)")
    Optional<CondPag> findCondPagByCodCondPag(String codCondPag);

    @Query("select e from CondPag e order by e.codCondPag")
    List<CondPag> listCondPag();
}
