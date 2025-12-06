package com.holis.san01.repository;

import com.holis.san01.model.Item;
import com.holis.san01.model.PedVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedVendaRepository extends JpaRepository<PedVenda, Integer>, JpaSpecificationExecutor<PedVenda> {

    @Query("SELECT i FROM PedVenda i WHERE i.empresa = :empresa AND i.id = :id)")
    Optional<PedVenda> findById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT i FROM PedVenda i WHERE i.empresa = :empresa AND i.nrPedido = :nrPedido")
    Optional<Item> findByNrPedido(@Param("empresa") Integer empresa, @Param("nrPedido") Integer nrPedido);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM PedVenda i WHERE i.empresa = :empresa AND i.id = :id")
    boolean existsById(@Param("empresa") Integer empresa, @Param("id") Integer id);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM PedVenda i WHERE i.empresa = :empresa AND i.codEntd = :codEntd")
    boolean existsByCodEntd(@Param("empresa") Integer empresa, @Param("codEntd") Integer codEntd);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM PedVenda i WHERE i.empresa = :empresa AND i.nrPedido = :nrPedido")
    boolean existsByNrPedido(@Param("empresa") Integer empresa, @Param("nrPedido") Integer nrPedido);

    @Query("DELETE FROM PedVenda i WHERE i.empresa = :empresa AND i.id = :id")
    void deleteById(@Param("empresa") Integer empresa, @Param("id") Integer id);
}
