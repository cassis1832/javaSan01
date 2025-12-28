package com.holis.san01.repository;

import com.holis.san01.model.UnidMedida;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidMedidaRepository extends JpaRepository<UnidMedida, String> {

    boolean existsBycodUniMed(String codUniMed);

    @Nonnull
    @Query("select u from UnidMedida u Where upper(u.codUniMed) =  upper(?1)")
    Optional<UnidMedida> findById(@Nonnull String codUniMed);

    @Query("select u from UnidMedida u Where u.status =  ?1 order by u.sequencia, u.codUniMed")
    List<UnidMedida> listUniMed(int status);
}
