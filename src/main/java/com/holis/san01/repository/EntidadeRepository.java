package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>, JpaSpecificationExecutor<Entidade> {

    boolean existsById(@Nonnull Integer codEntd);

    @Nonnull
    Optional<Entidade> findById(@Nonnull Integer codEntd);

    @Query("UPDATE Entidade SET status = :status WHERE codEntd = :codEntd")
    void archive(@Param("codEntd") Integer codEntd, @Param("status") Integer status);
}
