package com.holis.san01.specs;

import com.holis.san01.model.VwItem;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class VwItemSpecifications {

    public static Specification<VwItem> hasStatus(Integer status) {

        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<VwItem> hasTpProd(String tpProd) {

        return (root, query, cb) -> cb.equal(cb.upper(root.get("tpProd")), tpProd.toUpperCase());
    }

    public static Specification<VwItem> hasFiltro(String filtro) {

        return (root, query, cb) -> {
            Predicate codItemLike = cb.like(cb.upper(root.get("codItem")), "%" + filtro.toUpperCase() + "%");
            Predicate descricaoLike = cb.like(cb.upper(root.get("descricao")), "%" + filtro.toUpperCase() + "%");
            return cb.or(codItemLike, descricaoLike);
        };
    }
}
