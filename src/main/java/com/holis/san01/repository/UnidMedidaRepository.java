package com.holis.san01.repository;

import com.holis.san01.model.UnidMedida;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidMedidaRepository extends JpaRepository<UnidMedida, Integer> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM UnidMedida i WHERE i.empresa = :empresa AND LOWER(i.codUniMed) = LOWER(:codUniMed)")
    boolean existsBycodUniMed(@Param("empresa") Integer empresa, @Param("codUniMed") String codUniMed);

    @Query("SELECT u FROM UnidMedida u WHERE u.empresa = :empresa AND LOWER(u.codUniMed) =  LOWER(:codUniMed)")
    Optional<UnidMedida> findByCodUniMed(@Param("empresa") Integer empresa, @Param("codUniMed") String codUniMed);

    @Query("SELECT u FROM UnidMedida u WHERE u.empresa = :empresa AND u.status =  :status ORDER BY u.sequencia, u.codUniMed")
    List<UnidMedida> listUniMed(@Param("empresa") Integer empresa, @Param("status") Integer status);

    @Query("DELETE FROM UnidMedida e WHERE e.empresa = :empresa AND Lower(e.codUniMed) = Lower(:codUniMed)")
    int deleteByCodUniMed(Integer empresa, @NotNull String codUniMed);
}
