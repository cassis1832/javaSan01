package com.holis.san01.specs;

import com.holis.san01.model.VwPedVenda;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class VwPedVendaSpecifications {

    public static Specification<VwPedVenda> hasStatus(Integer status) {

        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<VwPedVenda> hasCodEntd(Integer codEntd) {

        return (root, query, cb) -> cb.equal(root.get("codEntd"), codEntd);
    }

    public static Specification<VwPedVenda> hasFiltro(String filtro) {

        return (root, query, cb) -> {
            Predicate nomeLike = cb.like(cb.upper(root.get("nome")), "%" + filtro.toUpperCase() + "%");
            return cb.or(nomeLike);
        };
    }

}
