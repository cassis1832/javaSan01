package com.holis.san01.repository;

import com.holis.san01.model.VwPedVenda;
import org.hibernate.annotations.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Immutable
public interface VwPedVendaRepository extends JpaRepository<VwPedVenda, Integer>, JpaSpecificationExecutor<VwPedVenda> {
}

