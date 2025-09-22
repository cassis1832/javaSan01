package com.holis.san01.specs;

import com.holis.san01.model.VwPedItem;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class VwPedItemSpecifications {

    public static Specification<VwPedItem> hasStatus(Integer status) {

        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<VwPedItem> hasNrPedido(Integer nrPedido) {

        return (root, query, cb) -> cb.equal(root.get("nrPedido"), nrPedido);
    }

    public static Specification<VwPedItem> hasCodEntd(Integer codEntd) {

        return (root, query, cb) -> cb.equal(root.get("codEntd"), codEntd);
    }

    public static Specification<VwPedItem> hasCodItem(String codItem) {

        return (root, query, cb) -> cb.equal(root.get("codItem"), codItem);
    }

    public static Specification<VwPedItem> hasFiltro(String filtro) {

        return (root, query, cb) -> {
            Predicate nomeLike = cb.like(cb.upper(root.get("nome")), "%" + filtro.toUpperCase() + "%");
            Predicate descricaoItemLike = cb.like(cb.upper(root.get("descricaoItem")), "%" + filtro.toUpperCase() + "%");
            return cb.or(nomeLike, descricaoItemLike);
        };
    }
}
