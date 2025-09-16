package com.holis.san01.specs;

import com.holis.san01.model.VwTituloAp;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class VwTituloApSpecifications {

    public static Specification<VwTituloAp> hasStatus(Integer status) {

        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<VwTituloAp> hasCodEntd(Integer codEntd) {

        return (root, query, cb) -> cb.equal(root.get("codEntd"), codEntd);
    }

    public static Specification<VwTituloAp> hasCodEspDoc(String codEspDoc) {

        return (root, query, cb) -> cb.equal(cb.upper(root.get("codEspDoc")), codEspDoc.toUpperCase());
    }

    public static Specification<VwTituloAp> hashasDocId(Integer docId) {

        return (root, query, cb) -> cb.equal(root.get("docId"), docId);
    }

    public static Specification<VwTituloAp> vencidos() {

        return (root, query, cb) -> cb.lessThan(root.get("dtVencto"), LocalDate.now());
    }

    public static Specification<VwTituloAp> aVencer() {

        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dtVencto"), LocalDate.now());
    }

    public static Specification<VwTituloAp> hasFiltro(String filtro) {

        return (root, query, cb) -> {
            Predicate nomeLike = cb.like(cb.upper(root.get("nome")), "%" + filtro.toUpperCase() + "%");
            Predicate descricaoLike = cb.like(cb.upper(root.get("descricao")), "%" + filtro.toUpperCase() + "%");
            return cb.or(nomeLike, descricaoLike);
        };
    }
}
