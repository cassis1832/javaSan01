package com.holis.san01.services;

import com.holis.san01.utils.SpecificationUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseService<E, ID> {

    protected final JpaRepository<E, ID> repository;
    protected final JpaSpecificationExecutor<E> specRepository;

    protected BaseService(JpaRepository<E, ID> repository,
                          JpaSpecificationExecutor<E> specRepository) {
        this.repository = repository;
        this.specRepository = specRepository;
    }

    public abstract E save(E entity);

    public abstract E update(E entity);

    public abstract void deleteById(ID id);

    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    public List<E> findList(Map<String, String> filters) {
        Specification<E> spec = SpecificationUtils.createSpecification(filters);
        return specRepository.findAll(spec);
    }
}
