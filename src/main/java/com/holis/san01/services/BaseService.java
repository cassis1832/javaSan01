package com.holis.san01.services;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Base Service
 *
 * @param <ENTITY>
 * @param <ID>
 * @param <VIEW>
 */
public interface BaseService<ENTITY, ID, VIEW> {

    Optional<ENTITY> findById(ID id);

    ENTITY save(@Nonnull ENTITY entity);

    ENTITY update(@Nonnull final ENTITY entity);

    void delete(@Nonnull ID id);

    void archive(@Nonnull ID id, Boolean status);

    List<ENTITY> findList(Map<String, String> filters);

    Page<VIEW> findPage(Pageable pageable, Map<String, String> filtros);
}
