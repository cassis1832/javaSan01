package com.holis.san01.repository;

import com.holis.san01.model.PedVenda;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedVendaRepository extends JpaRepository<PedVenda, Integer>, JpaSpecificationExecutor<PedVenda> {

    boolean existsByCodEntd(Integer codEntd);

    boolean existsById(@Nonnull Integer nrPedido);
}
