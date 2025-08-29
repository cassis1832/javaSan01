package com.holis.san01.repository;

import com.holis.san01.model.VwTituloAp;
import org.hibernate.annotations.Immutable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

@Immutable
public interface VwTituloApRepository extends JpaRepository<VwTituloAp, Integer> {
    @Query("select vw from VwTituloAp vw where archive = ?1 ")
    List<VwTituloAp> ListVwTituloAp(boolean archive);

    @Query("select vw from VwTituloAp vw where archive = ?1 ")
    Page<VwTituloAp> PageVwTituloAp(boolean archive, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive = ?1 " +
            "and (vw.dtVencto < ?2 or vw.dtVencto < ?2) ")
    Page<VwTituloAp> PageVwTituloApVencidos(boolean archive, LocalDate dtVencto, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive = ?1 " +
            "and (vw.dtVencto < ?2 or vw.dtVencto < ?2)")
    Page<VwTituloAp> PageVwTituloApVencidos(boolean archive, LocalDate dtVencto, String filterText, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive = ?1 " +
            "and (vw.dtVencto >= ?2 or vw.dtVencto >= ?2) ")
    Page<VwTituloAp> PageVwTituloApVencer(boolean archive, LocalDate dtVencto, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive = ?1 " +
            "and (vw.dtVencto >= ?2 or vw.dtVencto >= ?2) ")
    Page<VwTituloAp> PageVwTituloApVencer(boolean archive, LocalDate dtVencto, String filterText, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive = ?1 "
            + "and nome like concat('%',?2,'%')")
    Page<VwTituloAp> PageVwTituloAp(boolean archive, String filterText, Pageable pageable);

    @Query("select vw from VwTituloAp vw where archive  = ?1 "
            + "and codEspDoc = ?2 ")
    List<VwTituloAp> ListVwTituloAp(boolean archive, String codEspDoc);

    @Query("select vw from VwTituloAp vw where codEspDoc = ?1 "
            + "and docId   = ?2")
    List<VwTituloAp> ListVwTituloAp(String codEspDoc, Integer docId);

    @Query("select vw from VwTituloAp vw where archive  = ?1 "
            + "and codEspDoc in (?2) ")
    List<VwTituloAp> ListVwTituloAp(boolean archive, List<String> codEspDoc);

}
