package com.holis.san01.repository;

import com.holis.san01.model.EspDoc;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspDocRepository extends JpaRepository<EspDoc, String>, JpaSpecificationExecutor<EspDoc> {

    @Nonnull
    @Query("SELECT e FROM EspDoc e WHERE LOWER(e.codEspDoc) = LOWER(?1)")
    Optional<EspDoc> findById(@Nonnull @Param("codEspDoc") String codEspDoc);
}
