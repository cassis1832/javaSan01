package com.holis.san01.repository;

import com.holis.san01.model.Ncm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NcmRepository extends JpaRepository<Ncm, String> {
    @Query("select t from Ncm t Where upper(t.codNcm) =  upper(?1)")
    Optional<Ncm> findNcm(String codNcm);

    @Query("select i from Ncm i Where i.status  = ?1")
    Page<Ncm> listNcm(int status, Pageable pageable);

    @Query("select i from Ncm i where i.status = ?1 and " +
            "(concat(i.codNcm, i.descricao) like concat('%',?2,'%')) ")
    Page<Ncm> listNcm(int status, String filterText, Pageable pageable);
}