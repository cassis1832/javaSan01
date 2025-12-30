package com.holis.san01.services;

import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.EspDoc;
import com.holis.san01.repository.EspDocRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service para tratamento da tabela de especie de documento
 */
@Service
@RequiredArgsConstructor
public class EspDocService implements BaseService<EspDoc, String, EspDoc> {

    private final EspDocRepository espDocRepository;

    @Override
    public EspDoc findById(@Nonnull String id) {
        return espDocRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
    }

    @Override
    @Transactional
    public EspDoc create(@Nonnull EspDoc espDoc) {
        return null;
    }

    @Override
    @Transactional
    public EspDoc update(@Nonnull EspDoc espDoc) {
        return null;
    }

    @Override
    @Transactional
    public void delete(@Nonnull String s) {

    }

    @Override
    public List<EspDoc> findAll(Map<String, String> filters) {
        return List.of();
    }

    @Override
    public Page<EspDoc> findPage(Pageable pageable, Map<String, String> filtros) {
        return null;
    }

    @Override
    public void arquivar(@Nonnull String s) {

    }

    @Override
    public void desarquivar(@Nonnull String s) {

    }

}

