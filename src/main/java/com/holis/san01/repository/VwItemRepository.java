package com.holis.san01.repository;

import com.holis.san01.model.VwItem;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * View de ITENS
 */
@Immutable
public interface VwItemRepository extends JpaRepository<VwItem, String>, JpaSpecificationExecutor<VwItem> {

    @Query("select i from VwItem i Where i.status = ?1")
    Page<VwItem> pageVwItens(Integer status, Pageable pageable);

    @Query("select i from VwItem i where i.status = ?1 and " +
            "(concat(i.codItem, i.descricao) like concat('%',?2,'%')) ")
    Page<VwItem> pageVwItens(Integer status, String filterText, Pageable pageable);

    @Query("select i from VwItem i where i.status = ?2 and " +
            "upper(i.tpProd) = upper(?1)")
    Page<VwItem> pageVwItensPorTipo(String tpProd, Integer status, Pageable pageable);

    @Query("select i from VwItem i where i.status = ?2 and " +
            "upper(i.tpProd) = upper(?1) and " +
            "(i.codItem like concat('%',?3,'%') or i.descricao like concat('%',?3,'%')) ")
    Page<VwItem> pageVwItensPorTipo(String tipoItem, Integer status, String filterText, Pageable pageable);
}