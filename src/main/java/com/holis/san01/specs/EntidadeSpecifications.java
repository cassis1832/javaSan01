package com.holis.san01.specs;

import com.holis.san01.model.Entidade;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class EntidadeSpecifications {

    public static Specification<Entidade> hasStatus(Integer status) {

        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Entidade> cliente() {

        return (root, query, cb) -> cb.equal(root.get("indCliente"), true);
    }

    public static Specification<Entidade> fornecedor() {

        return (root, query, cb) -> cb.equal(root.get("indFornec"), true);
    }

    public static Specification<Entidade> ambos() {

        return (root, query, cb) -> {
            Predicate cliente = cb.equal(root.get("indCliente"), true);
            Predicate fornecedor = cb.equal(root.get("indFornec"), true);
            return cb.or(cliente, fornecedor);
        };
    }

    public static Specification<Entidade> nenhum() {

        return (root, query, cb) -> {
            Predicate cliente = cb.equal(root.get("indCliente"), false);
            Predicate fornecedor = cb.equal(root.get("indFornec"), false);
            return cb.and(cliente, fornecedor);
        };
    }

    public static Specification<Entidade> hasFiltro(String filtro) {

        return (root, query, cb) -> {
            Predicate nomeLike = cb.like(cb.upper(root.get("nome")), "%" + filtro.toUpperCase() + "%");
            Predicate razaoSocialLike = cb.like(cb.upper(root.get("razaoSocial")), "%" + filtro.toUpperCase() + "%");
            return cb.or(nomeLike, razaoSocialLike);
        };
    }
}
