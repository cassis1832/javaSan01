package com.holis.san01.repository;

import com.holis.san01.model.VwTituloAp;
import org.hibernate.annotations.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Immutable
public interface VwTituloApRepository extends JpaRepository<VwTituloAp, Integer>, JpaSpecificationExecutor<VwTituloAp> {
}
