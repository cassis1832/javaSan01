package com.holis.san01.services;

import com.holis.san01.model.Situacao;
import com.holis.san01.repository.SituacaoRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service para tratamento da tabela de situações
 */
@Service
@RequiredArgsConstructor
public class SituacaoService implements BaseService<Situacao, Integer, String> {

    private final SituacaoRepository situacaoRepository;

    @Override
    public Situacao findById(@Nonnull Integer integer) {
        return null;
    }

    @Override
    public Situacao create(@Nonnull Situacao situacao) {
        return null;
    }

    @Override
    public Situacao update(@Nonnull Situacao situacao) {
        return null;
    }

    @Override
    public void deleteById(@Nonnull Integer integer) {

    }

    @Override
    public List<Situacao> findList(Map<String, String> filters) {
        return List.of();
    }

    @Override
    public Page<String> findPage(Pageable pageable, Map<String, String> filtros) {
        return null;
    }

    @Override
    public void archive(@Nonnull Integer integer) {

    }

    @Override
    public void unarchive(@Nonnull Integer integer) {

    }
}
