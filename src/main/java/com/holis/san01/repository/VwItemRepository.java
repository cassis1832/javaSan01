package com.holis.san01.repository;

import com.holis.san01.model.VwItem;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Immutable
public interface VwItemRepository extends JpaRepository<VwItem, String>, JpaSpecificationExecutor<VwItem> {
}