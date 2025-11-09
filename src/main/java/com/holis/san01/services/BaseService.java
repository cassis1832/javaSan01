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

    void deleteById(@Nonnull ID id);

    List<ENTITY> listEntity(Map<String, String> filters);

    Page<VIEW> pageView(Pageable pageable, Map<String, String> filtros);

    void checkDelete(ID id);

    void archive(ID id);

    void unarchive(ID id);
}
