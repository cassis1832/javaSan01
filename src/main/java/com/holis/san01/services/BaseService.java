package com.holis.san01.services;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Base Service
 *
 * @param <ENTITY>
 * @param <ID>
 * @param <VIEW>
 */
public interface BaseService<ENTITY, ID, VIEW> {

    ENTITY findById(@Nonnull ID id);

    ENTITY create(@Nonnull ENTITY entity);

    ENTITY update(@Nonnull final ENTITY entity);

    void deleteById(@Nonnull ID id);

    List<ENTITY> findList(Map<String, String> filters);

    Page<VIEW> findPage(Pageable pageable, Map<String, String> filtros);

    void archive(@Nonnull ID id);

    void unarchive(@Nonnull ID id);
}
