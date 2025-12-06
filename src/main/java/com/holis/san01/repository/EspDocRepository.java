package com.holis.san01.repository;

import com.holis.san01.model.EspDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspDocRepository extends JpaRepository<EspDoc, Integer>, JpaSpecificationExecutor<EspDoc> {

    @Query("select e from EspDoc e Where e.empresa = :empresa AND upper(e.codEspDoc) = upper(:codEspDoc)")
    Optional<EspDoc> findByCodEspDoc(@Param("empresa") Integer empresa, @Param("codEspDoc") String codEspDoc);

    @Query("DELETE FROM EspDoc i WHERE i.empresa = :empresa AND LOWER(i.codEspDoc) = LOWER(:codEspDoc)")
    void deleteBycodEspDoc(@Param("empresa") Integer empresa, @Param("codEspDoc") String codEspDoc);
}
