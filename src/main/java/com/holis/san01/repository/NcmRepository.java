package com.holis.san01.repository;

import com.holis.san01.model.Ncm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NcmRepository extends JpaRepository<Ncm, Integer>, JpaSpecificationExecutor<Ncm> {

    @Query("SELECT t FROM Ncm t WHERE t.empresa = :empresa AND UPPER(t.codNcm) =  UPPER(codNcm)")
    Optional<Ncm> findByCodNcm(@Param("empresa") Integer empresa, @Param("codNcm") String codNcm);

    @Query("SELECT i FROM Ncm i WHERE i.empresa = :empresa AND i.status = :status")
    Page<Ncm> listNcm(@Param("empresa") Integer empresa, @Param("status") Integer status, Pageable pageable);

    @Query("SELECT i FROM Ncm i WHERE i.empresa = :empresa AND i.status = ?1 and " +
            "(concat(i.codNcm, i.descricao) like concat('%',:filterText,'%')) ")
    Page<Ncm> listNcm(@Param("empresa") Integer empresa, @Param("status") Integer status, @Param("filterText") String filterText, Pageable pageable);

    // O retorno int informa o número de registros afetados.
    @Query("DELETE FROM Ncm e WHERE e.empresa = :empresa AND Lower(e.codNcm) = Lower(:codNcm)")
    void deleteByCodNcm(@Param("empresa") Integer empresa, @Param("codNcm") String codNcm);
}