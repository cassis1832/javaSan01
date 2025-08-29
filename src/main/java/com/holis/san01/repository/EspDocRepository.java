package com.holis.san01.repository;

import com.holis.san01.model.EspDoc;
import com.holis.san01.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspDocRepository extends JpaRepository<EspDoc, String> {

    @Query("select e from EspDoc e Where codEspDoc =  upper(?1)")
    Optional<EspDoc> getEspDoc(String codEspDoc);

    @Query("select e from EspDoc e where e.tipoMovto = ?1")
    Page<EspDoc> pageEspDoc(boolean tipo, Pageable pageable);
}
