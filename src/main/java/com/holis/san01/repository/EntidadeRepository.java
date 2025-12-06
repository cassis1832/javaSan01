package com.holis.san01.repository;

import com.holis.san01.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>, JpaSpecificationExecutor<Entidade> {

    @Query("SELECT i FROM Entidade i WHERE i.empresa = :empresa AND i.id = :id)")
    Optional<Entidade> findById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Entidade r WHERE r.empresa = :empresa AND r.codEntd = :codEntd")
    boolean existsByCodEntd(@Param("empresa") Integer empresa, @Param("codEntd") Integer codEntd);

    @Query("DELETE FROM Entidade i WHERE i.empresa = :empresa AND i.id = :id")
    void deleteById(@Param("empresa") Integer empresa, @Param("id") Integer id);
}
