package com.holis.san01.specs;

import com.holis.san01.model.CondPag;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CondPagSpecifications {

    public static Specification<CondPag> hasStatus(Integer status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<CondPag> pagar() {
        return (root, query, cb) -> cb.equal(root.get("ctPag"), true);
    }

    public static Specification<CondPag> receber() {
        return (root, query, cb) -> cb.equal(root.get("ctRec"), true);
    }

    public static Specification<CondPag> ambos() {
        return (root, query, cb) -> {
            Predicate pagar = cb.equal(root.get("ctPag"), true);
            Predicate receber = cb.equal(root.get("ctRec"), true);
            return cb.or(pagar, receber);
        };
    }

    public static Specification<CondPag> nenhumTipo() {
        return (root, query, cb) -> {
            Predicate pagar = cb.equal(root.get("ctPag"), false);
            Predicate receber = cb.equal(root.get("ctRec"), false);
            return cb.and(pagar, receber);
        };
    }

    public static Specification<CondPag> hasFiltro(String filtro) {
        return (root, query, cb) -> {
            Predicate condCondPagLike = cb.like(cb.upper(root.get("codCondPag")), "%" + filtro.toUpperCase() + "%");
            Predicate descricaoLike = cb.like(cb.upper(root.get("descricao")), "%" + filtro.toUpperCase() + "%");
            return cb.or(condCondPagLike, descricaoLike);
        };
    }
}
